package app.is_mobile.filmoteca.data.remote.mapper

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import app.is_mobile.filmoteca.data.remote.dto.FilmDto
import android.util.Base64
import app.is_mobile.filmoteca.domain.model.Film

fun FilmDto.toDomain(): Film {
    val coverBitmap = this.image?.let { base64String ->
        val imageBytes = Base64.decode(base64String, Base64.DEFAULT)
        BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }
    return Film(
        id = this.id.toInt(),
        image = coverBitmap,
        title = this.title ?: "",
        director = this.director ?: "",
        year = this.year,
        genre = this.genre,
        format = this.format,
        imdbUrl = this.imdbUrl ?: "",
    )
}

fun Film.toDto(): FilmDto {
    val base64Cover = this.image?.let { bitmap ->
        val outputStream = java.io.ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT)
    }
    return FilmDto(
        id = this.id.toString(),
        title = this.title,
        image = base64Cover,
        director = this.director,
        year = this.year,
        genre = this.genre,
        format = this.format,
        imdbUrl = this.imdbUrl,
    )
}