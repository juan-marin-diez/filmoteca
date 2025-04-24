package app.is_mobile.filmoteca.domain.model;

import android.graphics.Bitmap

data class Film(
        var id: Int = 0,
        var image: Bitmap? = null, // Propiedades de la clase
        var title: String? = null,
        var director: String? = null,
        var year: Int = 0,
        var genre: Int = 0,
        var format: Int = 0,
        var imdbUrl: String? = null,
        var comments: String? = null
) {
    override fun toString(): String {
        // Al convertir a cadena mostramos su título
        return title ?: "<Sin título>"
    }

    /*companion object {
        const val  FORMAT_UNDEFINED = 0 // Formato no definido
        const val  GENRE_UNDEFINED = 0 // Género no definido
        const val FORMAT_DVD = 1 // Formatos
        const val FORMAT_BLURAY = 2
        const val FORMAT_DIGITAL = 3
        const val GENRE_ACTION = 1 // Géneros
        const val GENRE_COMEDY = 2
        const val GENRE_DRAMA = 3
        const val GENRE_SCIFI = 4
        const val GENRE_HORROR = 5
    }*/
}
