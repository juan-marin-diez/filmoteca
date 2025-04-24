package app.is_mobile.filmoteca.data.repository

import app.is_mobile.filmoteca.domain.model.Film
import app.is_mobile.filmoteca.domain.repository.FilmRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import android.util.Log
import app.is_mobile.filmoteca.data.remote.dto.FilmDto
import app.is_mobile.filmoteca.data.remote.mapper.toDomain
import app.is_mobile.filmoteca.data.remote.mapper.toDto
import app.is_mobile.filmoteca.domain.model.Format
import app.is_mobile.filmoteca.domain.model.Gender

class FilmRepositoryImpl : FilmRepository {
    override val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    override val filmsCollection: CollectionReference = db.collection("films")
    override val genders: CollectionReference = db.collection("gender_es")
    override val formats: CollectionReference = db.collection("format_es")

    override suspend fun addFilm(film: Film) {
        filmsCollection.add(film.toDto()).await()
    }

    override suspend fun getFilms(): List<Film> {
        return try {
            val snapshot = filmsCollection.get().await() // Obtiene los documentos de Firestore
            snapshot.documents.mapNotNull { it.toObject(FilmDto::class.java)?.toDomain()?.copy(id = it.id.toInt()) }
        } catch (e: Exception) {
            Log.e("F_error", "Error obtaining films: ${e.message}")
            emptyList()
        }
    }

    override suspend fun updateFilm(film: Film) {
        filmsCollection.document(film.toDto().id.toString()).set(film)
    }

    override suspend fun deleteFilm(film: Film) {
        filmsCollection.document(film.toDto().id.toString()).delete().await()
    }

    override suspend fun deleteMultipleFilms(selectedFilms: List<Film>) {
        val batch = db.batch() // Crea una operación en lote

        selectedFilms.forEach { film ->
            film.id.let { filmID ->
                batch.delete(filmsCollection.document(filmID.toString()))
            }
        }

        try {
            batch.commit().await()
            Log.i("F_info", "Correctly deleted selected films from Firestore")
        } catch (e: Exception) {
            Log.e("FS_error", "Error deleting selected films: ${e.message}")
        }
    }

    override fun listenToFilmsUpdates(onUpdate: (List<Film>) -> Unit) {
        filmsCollection.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                Log.e("F_error", "Error obtaining films: ${exception.message}")
                return@addSnapshotListener
            }
            val films = snapshot?.documents?.mapNotNull { it.toObject(FilmDto::class.java)?.toDomain()?.copy(id = it.id.toInt()) } ?: emptyList()
            onUpdate(films)
        }
    }

    override suspend fun getGenres(): List<Gender> {
        return try {
            val snapshot = genders.get().await()
            snapshot.documents.mapNotNull { it.toObject(Gender::class.java)?.copy(id = it.id.toInt()) }
        } catch (e: Exception) {
            Log.e("F_error", "Error obtaining films: ${e.message}")
            emptyList()
        }
    }

    override suspend fun getFormats(): List<Format> {
        return try {
            val snapshot = formats.get().await()
            snapshot.documents.mapNotNull { it.toObject(Format::class.java)?.copy(id = it.id.toInt()) }
        } catch (e: Exception) {
            Log.e("F_error", "Error obtaining films: ${e.message}")
            emptyList()
        }
    }
}