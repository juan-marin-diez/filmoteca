package app.is_mobile.filmoteca.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import app.is_mobile.filmoteca.R
import app.is_mobile.filmoteca.data.FilmDataSource

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FilmEditScreen(navController: NavHostController, indexOfFilm: Int) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppBar(
                showNavigationButton = true,
                showMenuButton = false,
                navController = navController
            )
        },
    ) {
        NewFilmEditScreenContent(navController = navController, innerPadding = it, indexOfFilm) }
}

@Composable
fun FilmEditScreenContent(navController: NavHostController, innerPadding: PaddingValues) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Editando película")
        Button(onClick = {
            navController.previousBackStackEntry?.savedStateHandle?.set("key_result", "RESULT_OK")
            navController.popBackStack()
        }) {
            Text("Guardar")
        }
        Button(onClick = {
            navController.previousBackStackEntry?.savedStateHandle?.set("key_result", "RESULT_CANCELED")
            navController.popBackStack()
        }) {
            Text("Cancelar")
        }
    }
}

@Composable
fun NewFilmEditScreenContent(
    navController: NavHostController,
    innerPadding: PaddingValues,
    indexOfFilm: Int
) {
    val film = FilmDataSource.films[indexOfFilm]
    var titulo by remember { mutableStateOf(film.title?.let { it }?:"") }
    var director: String by remember { mutableStateOf(film.director?.let { it }?:"") }
    var anyo: Int by remember { mutableIntStateOf(film.year?.let{ it.toInt()} ?:2000 ) }
    var url by remember { mutableStateOf(film.imdbUrl?.let { it }?:"") }
    var imagen by remember { mutableIntStateOf(film.imageResId) }
    var comentarios by remember { mutableStateOf(film.comments?.let { it }?:"") }
    var expandedGenero by remember { mutableStateOf(false) }
    var expandedFormato by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val generoList = context.resources.getStringArray(R.array.genero_list).toList()
    val formatoList = context.resources.getStringArray(R.array.formato_list).toList()
    var genero by remember { mutableIntStateOf(film.genre) }
    var formato by remember { mutableIntStateOf(film.format) }

    Column (
        modifier = Modifier.fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(0.2f),
                painter = painterResource(id = film.imageResId),
                contentDescription = "Cartel de ${film.title}"
            )
            Button(
                modifier = Modifier
                    .padding(start = 5.dp)
                    .fillMaxWidth(0.5f),
                onClick = {  }) {
                Text("Capturar fotografía")
            }
            Button(
                modifier = Modifier
                    .padding(start = 5.dp)
                    .fillMaxWidth(),
                onClick = {  }) {
                Text("Seleccionar imagen")
            }
        }
        TextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text("Título") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
        TextField(
            value = director,
            onValueChange = { director = it },
            label = { Text("Director") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
        TextField(
            value = anyo.toString(),
            onValueChange = { anyo = it.toInt() },
            label = { Text("Año") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            value = url,
            onValueChange = { url = it },
            label = { Text("Enlace a IMDB") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
        TextButton (
            onClick = { expandedGenero = !expandedGenero },
            modifier = Modifier
                .padding(5.dp)
                .background(color = MaterialTheme.colorScheme.background)
                .fillMaxWidth()
        ) {
            Text(text = "Género")
            if(genero>0)
                Text(text = " - " +generoList[genero])
        }
        DropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = expandedGenero,
            onDismissRequest = { expandedGenero = !expandedGenero },
        ) {
            generoList.forEachIndexed { index, option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = { genero = index; expandedGenero = !expandedGenero }
                )
            }
        }
        TextButton (
            onClick = { expandedFormato = !expandedFormato },
            modifier = Modifier
                .padding(5.dp)
                .background(color = MaterialTheme.colorScheme.background)
                .fillMaxWidth()
        ) {
            Text(text = "Formato")
            if(formato>0)
                Text(text = " - " + formatoList[formato])
        }
        DropdownMenu(
            expanded = expandedFormato,
            onDismissRequest = { expandedFormato = !expandedFormato }
        ) {
            formatoList.forEachIndexed { index, option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = { formato = index; expandedFormato = !expandedFormato }
                )
            }
        }
        TextField(
            value = comentarios,
            onValueChange = { comentarios = it },
            label = { Text("Comentarios") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
        Row(modifier = Modifier.fillMaxWidth())
        {
            Button(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(0.5f),
                onClick = {
                    try
                    {
                        FilmDataSource.films[indexOfFilm].title = titulo
                        FilmDataSource.films[indexOfFilm].director = director
                        FilmDataSource.films[indexOfFilm].year = anyo
                        FilmDataSource.films[indexOfFilm].imdbUrl = url
                        FilmDataSource.films[indexOfFilm].comments = comentarios
                        FilmDataSource.films[indexOfFilm].imageResId = imagen
                        FilmDataSource.films[indexOfFilm].genre = genero
                        FilmDataSource.films[indexOfFilm].format = formato
                        navController.previousBackStackEntry?.savedStateHandle?.set("key_result", "RESULT_OK")
                        navController.popBackStack()
                    }
                    catch (e: Exception)
                    {
                        navController.previousBackStackEntry?.savedStateHandle?.set("key_result", "RESULT_ERROR")
                        navController.popBackStack()
                    }
                }) {
                Text("Guardar")
            }
            Button(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(),
                onClick = {
                    navController.previousBackStackEntry?.savedStateHandle?.set("key_result", "RESULT_CANCELED")
                    navController.navigateUp()
                }) {
                Text("Cancelar")
            }
        }
    }
}

@Composable
@Preview
fun NewFilmEditScreenContentPreview() {
    NewFilmEditScreenContent(
        navController = NavHostController(context = LocalContext.current),
        innerPadding = PaddingValues(0.dp),
        indexOfFilm = 1
    )
}