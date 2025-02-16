package app.is_mobile.filmoteca.data

fun DelFilm(filmsToDelete: List<Film>) {
    for (film in filmsToDelete) {
        FilmDataSource.films.remove(film)
    }

}