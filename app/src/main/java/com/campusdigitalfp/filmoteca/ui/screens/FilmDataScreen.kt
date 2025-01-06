package com.campusdigitalfp.filmoteca.ui.screens
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.R
import com.campusdigitalfp.filmoteca.data.FilmDataSource
import com.campusdigitalfp.filmoteca.navigation.Screens


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FilmDataScreen(navController: NavHostController, indexOfFilm: Int) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppBar(
                showNavigationButton = true,
                showMenuButton = false,
                navController = navController
            )
        },
    ) { innerPadding ->
        NewFilmDataScreenContent(navController = navController, innerPadding, indexOfFilm) }
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

@Composable
fun NewFilmDataScreenContent(
    navController: NavHostController,
    innerPadding: PaddingValues,
    indexOfFilm: Int
) {
    val film = FilmDataSource.films[indexOfFilm]
    val context = LocalContext.current
    Column (modifier = Modifier.fillMaxSize()
        .padding(innerPadding)) {
        Row (verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier.padding(8.dp)
                    .fillMaxWidth(0.5f),
                painter = painterResource(id=film.imageResId),
                contentDescription = "Cartel de la película"
            )
            Column {
                Text(text = film.title?.let { it }?:"", style = MaterialTheme.typography.titleMedium)
                Text(text = "Director:", fontWeight = FontWeight.Bold)
                Text(film.director?.let { it }?:"")
                Text("Año:", fontWeight = FontWeight.Bold)
                Text(film.year.toString())
                if(film.genre>=0 && film.format>=0)
                    Text(context.resources.getStringArray(R.array.genero_list)[film.genre+1] + " - " +
                            context.resources.getStringArray(R.array.formato_list)[film.format+1])
                else
                    Text(context.resources.getStringArray(R.array.genero_list)[film.genre+1] +
                            context.resources.getStringArray(R.array.formato_list)[film.format+1])
            }
        }
        Button(modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 5.dp), onClick = { abrirPaginaWeb(film.imdbUrl?.let { it }?:"https://www.imdb.com/",
            context = context
        ) }) {
            Text("VER EN imdb")
        }
        Text(text = film.comments?.let { it }?:"", style = MaterialTheme.typography.bodyMedium)
        Row(Modifier.fillMaxWidth()
            .padding(horizontal = 5.dp),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(modifier = Modifier.fillMaxWidth(0.5f), onClick = { navController.navigateUp() }) {
                Text(stringResource(R.string.go_back))
            }
            Button(modifier = Modifier.fillMaxWidth()
                .padding(start = 5.dp), onClick = { navController.navigate(Screens.FilmEdit.route + "/${indexOfFilm}") }) {
                Text(stringResource(R.string.editar_pel_cula))
            }
        }
    }
}