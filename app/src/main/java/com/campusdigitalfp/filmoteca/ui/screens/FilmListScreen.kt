package com.campusdigitalfp.filmoteca.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.R
import com.campusdigitalfp.filmoteca.data.FilmDataSource
import com.campusdigitalfp.filmoteca.navigation.Screens

@Composable
fun FilmListScreen(navController: NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppBar(
                showNavigationButton = false,
                showMenuButton = true,
                navController = navController
            )
        },
    ) { innerPadding ->
        NewFilmListScreenContent(navController, innerPadding) }
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

@Composable
fun NewFilmListScreenContent (navController: NavHostController, innerPadding: PaddingValues) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
            .padding(innerPadding),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        items(items = FilmDataSource.films,)
        { film ->
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable(
                    onClick = { navController.navigate(Screens.FilmData.route + "/${FilmDataSource.films.indexOf(film)}") })
            ) {
                Image(
                    modifier = Modifier.padding(5.dp)
                        .fillMaxWidth(0.25f),
                    painter = painterResource(id = film.imageResId),
                    contentDescription = "Cartel de ${film.title}"
                )
                Column(modifier = Modifier.padding(5.dp)
                    .fillMaxWidth()) {
                        film.title?.let { Text(text = it, style = MaterialTheme.typography.titleMedium) }
                        film.director?.let { Text(text = it, style = MaterialTheme.typography.bodyMedium) }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewFilmListScreen() {
    NewFilmListScreenContent(
        navController = NavHostController(LocalContext.current),
        innerPadding = PaddingValues(5.dp)
    )
}
