package com.campusdigitalfp.filmoteca.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.campusdigitalfp.filmoteca.ui.screens.AboutScreen
import com.campusdigitalfp.filmoteca.ui.screens.FilmDataScreen
import com.campusdigitalfp.filmoteca.ui.screens.FilmEditScreen
import com.campusdigitalfp.filmoteca.ui.screens.FilmListScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.FilmList.route) {
        composable(route = Screens.FilmList.route) {
            FilmListScreen(navController)
        }
        composable(route = Screens.About.route) {
            AboutScreen(navController)
        }
        composable(route = Screens.FilmData.route + "/{indexOfFilm}") {
            FilmDataScreen(
                navController = navController,
                indexOfFilm = it.arguments?.let { it.getString("indexOfFilm")?.toInt() }?:-1
            )
        }
        composable(route = Screens.FilmEdit.route + "/{indexOfFilm}") {
            FilmEditScreen(
                navController = navController,
                indexOfFilm = it.arguments?.getString("indexOfFilm")?.let { it.toInt() }?:-1
            )
        }
    }
}