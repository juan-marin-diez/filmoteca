package com.campusdigitalfp.filmoteca.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.R
import com.campusdigitalfp.filmoteca.navigation.Screens

@Composable
fun FilmEditScreen(navController: NavHostController) {
    FilmEditScreenContent(navController)
}

@Composable
fun FilmEditScreenContent(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Editando película")
        Button(onClick = { navController.navigate(Screens.FilmEdit.route) }) {
            Text(stringResource(R.string.editar_pel_cula))
        }
        Button(onClick = { navController.navigate(Screens.FilmList.route) }) {
            Text(stringResource(R.string.volver_a_la_principal))
        }
    }
}
