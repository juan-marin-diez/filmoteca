package app.is_mobile.filmoteca.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.is_mobile.filmoteca.data.repository.FilmRepositoryImpl
import app.is_mobile.filmoteca.domain.model.Film
import app.is_mobile.filmoteca.domain.model.Format
import app.is_mobile.filmoteca.domain.model.Gender
import app.is_mobile.filmoteca.domain.repository.FilmRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FilmViewModel: ViewModel() {
    private val repository : FilmRepository = FilmRepositoryImpl()

    private val _films = MutableStateFlow<List<Film>>(emptyList())
    val films: StateFlow<List<Film>> get() = _films

    private val _selectedFilms = mutableStateListOf<Film>()
    val selectedFilms: SnapshotStateList<Film> get() = _selectedFilms

    private val _genres = MutableStateFlow<List<Gender>>(emptyList())
    val genres: StateFlow<List<Gender>> get() = _genres

    private val _formats = MutableStateFlow<List<Format>>(emptyList())
    val formats: StateFlow<List<Format>> get() = _formats

    init {
        listenToFilms()
    }

    private fun listenToFilms() {
        repository.listenToFilmsUpdates { updatedFilms ->
            _films.value = updatedFilms
        }
    }

    private fun fetchFilms() {
        viewModelScope.launch {
            _films.value = repository.getFilms()
        }
    }

    fun getGenres() {
        viewModelScope.launch {
            _genres.value = repository.getGenres()
        }
    }

    fun getFormats() {
        viewModelScope.launch {
            _formats.value = repository.getFormats()
        }
    }

    fun addFilm(film: Film) {
        viewModelScope.launch {
            repository.addFilm(film)
            fetchFilms()
        }
    }

    fun updateFilm(film: Film) {
        viewModelScope.launch {
            repository.updateFilm(film)
            fetchFilms()
        }
    }

    fun deleteFilm(film: Film) {
        viewModelScope.launch {
            repository.deleteFilm(film)
            fetchFilms()
        }
    }

    fun deleteSelectedFilms(selectedFilms: List<Film>) {
        viewModelScope.launch {
            repository.deleteMultipleFilms(selectedFilms)
            fetchFilms()
        }
    }

    fun addFilmToDelete(film: Film) {
        _selectedFilms.add(film)
    }

    fun removeFilmToDelete(film: Film) {
        _selectedFilms.remove(film)
    }

    fun emptyFilmsToDelete() {
        _selectedFilms.clear()
    }
}