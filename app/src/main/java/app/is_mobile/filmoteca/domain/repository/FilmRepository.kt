package app.is_mobile.filmoteca.domain.repository

import app.is_mobile.filmoteca.domain.model.Film
import app.is_mobile.filmoteca.domain.model.Format
import app.is_mobile.filmoteca.domain.model.Gender
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

interface FilmRepository {
    val db: FirebaseFirestore
    val filmsCollection: CollectionReference
    val genders: CollectionReference
    val formats: CollectionReference

    suspend fun addFilm(film: Film)

    suspend fun getFilms(): List<Film>

    suspend fun updateFilm(film: Film)

    suspend fun deleteFilm(film: Film)

    suspend fun deleteMultipleFilms(selectedHabits: List<Film>)

    fun listenToFilmsUpdates(onUpdate: (List<Film>) -> Unit)

    suspend fun getGenres(): List<Gender>

    suspend fun getFormats(): List<Format>

}