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
fun FilmDataScreen(navController: NavHostController, filmName: String?) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppBar(showNavigationButton = true, navController = navController)
        },
    ) { FilmDataScreenContent(navController = navController, filmName = filmName) }
}

@Composable
fun FilmDataScreenContent(navController: NavHostController, filmName: String?) {
    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    val result = savedStateHandle?.get<String>("key_result")
    var route = ""
    var film = ""
    if (filmName != null) {
        if (filmName == "A") {
            film = "Película A"
            route = Screens.FilmData.route + "/B"
        } else {
            film = "Película B"
            route = Screens.FilmData.route + "/A"
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.datos_de_la_pel_cula))
        Text(film)
        Button(onClick = { navController.navigate(route = route) }) { Text(stringResource(R.string.ver_pel_cula_relacionada)) }
        Button(onClick = { navController.navigate(Screens.FilmEdit.route) }) {
            Text(stringResource(R.string.editar_pel_cula))
        }
        result?.let {
            Text(text = it)
        }
        Button(onClick = { navController.navigate(Screens.FilmList.route) }) {
            Text(stringResource(R.string.volver_a_la_principal))
        }
    }
}


