package app.is_mobile.filmoteca.presentation.navigation

sealed class Screens (val route: String){
    object FilmList : Screens("home")
    object About : Screens("about")
    object FilmData : Screens("data")
    object FilmEdit : Screens("edit")
    object FilmAdd : Screens("add")
}