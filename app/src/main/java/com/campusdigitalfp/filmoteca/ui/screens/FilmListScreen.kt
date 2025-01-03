package com.campusdigitalfp.filmoteca.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.R
import com.campusdigitalfp.filmoteca.navigation.Screens


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FilmListScreen(navController: NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppBar(showNavigationButton = false, navController = navController)
        },
    ) { FilmListScreenContent(navController) }
}

@Composable
fun FilmListScreenContent(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { navController.navigate(Screens.FilmData.route + "/A") }) {
            Text(stringResource(R.string.ver_pel_cula_a))
        }
        Button(onClick = { navController.navigate(Screens.FilmData.route + "/B") }) {
            Text(stringResource(R.string.ver_pel_cula_b))
        }
        Button(onClick = { navController.navigate(Screens.About.route) }) {
            Text(stringResource(R.string.acerca_de))
        }
    }
}
