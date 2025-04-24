package app.is_mobile.filmoteca.data.remote.dto

data class FilmDto(
    var id: String = "",
    var image: String? = null, // Propiedades de la clase
    var title: String? = null,
    var director: String? = null,
    var year: Int = 0,
    var genre: Int = 0,
    var format: Int = 0,
    var imdbUrl: String? = null,
    var comments: String? = null
)
