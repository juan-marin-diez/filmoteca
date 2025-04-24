package app.is_mobile.filmoteca.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.is_mobile.filmoteca.presentation.screens.AboutScreen
import app.is_mobile.filmoteca.presentation.screens.FilmAddScreen
import app.is_mobile.filmoteca.presentation.screens.FilmDataScreen
import app.is_mobile.filmoteca.presentation.screens.FilmEditScreen
import app.is_mobile.filmoteca.presentation.screens.FilmListScreen
import app.is_mobile.filmoteca.presentation.viewmodel.FilmViewModel

@Composable
fun Navigation(viewModel: FilmViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.FilmList.route) {
        composable(route = Screens.FilmList.route) {
            FilmListScreen(navController)
        }
        composable(route = Screens.About.route) {
            AboutScreen(navController, viewModel)
        }
        composable(route = Screens.FilmData.route + "/{indexOfFilm}") {
            FilmDataScreen(
                navController = navController,
                indexOfFilm = it.arguments?.let { it.getString("indexOfFilm")?.toInt() } ?: -1,
                viewModel = viewModel
            )
        }
        composable(route = Screens.FilmEdit.route + "/{indexOfFilm}") {
            FilmEditScreen(
                navController = navController,
                indexOfFilm = it.arguments?.getString("indexOfFilm")?.toInt() ?:-1,
                viewModel = viewModel
            )
        }
        composable(route = Screens.FilmAdd.route) {
            FilmAddScreen (
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}