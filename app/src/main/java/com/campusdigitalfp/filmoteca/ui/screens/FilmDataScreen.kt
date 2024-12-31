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
fun FilmDataScreen(navController: NavHostController) {
    FilmDataScreenContent(navController)
}

@Composable
fun FilmDataScreenContent(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.datos_de_la_pel_cula))
        Button(onClick = { TODO() }) {
            Text(stringResource(R.string.ver_pel_cula_relacionada))
        }
        Button(onClick = { TODO() }) {
            Text(stringResource(R.string.editar_pel_cula))
        }
        Button(onClick = { navController.navigate(Screens.FilmList.route) }) {
            Text(stringResource(R.string.volver_a_la_principal))
        }
    }
}

