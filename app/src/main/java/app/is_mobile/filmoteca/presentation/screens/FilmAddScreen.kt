package app.is_mobile.filmoteca.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.NavHostController
import app.is_mobile.filmoteca.R
import app.is_mobile.filmoteca.domain.model.Film
import app.is_mobile.filmoteca.presentation.viewmodel.FilmViewModel

@Composable
fun FilmAddScreen(navController: NavHostController, viewModel: FilmViewModel) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppBar(
                showNavigationButton = true,
                showMenuButton = false,
                navController = navController,
                viewModel = viewModel
            )
        },
    ) { paddingValues ->
        FilmAddScreenContent(navController = navController, paddingValues, viewModel) }
}

@Composable
fun FilmAddScreenContent(
    navController: NavHostController,
    innerPadding: PaddingValues,
    viewModel: FilmViewModel
) {
    val film = Film()
    var titulo by remember { mutableStateOf("") }
    var director: String by remember { mutableStateOf("") }
    var anyo: Int by remember { mutableIntStateOf(2000 ) }
    var url by remember { mutableStateOf("") }
    val context = LocalContext.current
    var imagen by remember { mutableStateOf(ContextCompat.getDrawable(context, R.drawable.palomitas)?.toBitmap()) }
    //var imagen by remember { mutableStateOf(BitmapPainter(R.drawable.filmoteca)) }
    var comentarios by remember { mutableStateOf("") }
    var expandedGenero by remember { mutableStateOf(false) }
    var expandedFormato by remember { mutableStateOf(false) }
    //val generoList by viewModel.genres.collectAsState()
    val generoList = viewModel.getGenres()
    //val formatoList by viewModel.formats.collectAsState()
    val formatoList = viewModel.getFormats()
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
                painter = painterResource(id = R.drawable.palomitas),
                contentDescription = "Cartel de $titulo"
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
            onValueChange = {
                anyo = it.toIntOrNull() ?: 0
            },
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
                Text(text = " - " + generoList[genero])
        }
        DropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = expandedGenero,
            onDismissRequest = { expandedGenero = !expandedGenero },
        ) {
            generoList.forEachIndexed { index, option ->
                DropdownMenuItem(
                    text = { Text(option.gender) },
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
            if(formato>=0)
                Text(text = " - " + formatoList[formato])
        }
        DropdownMenu(
            expanded = expandedFormato,
            onDismissRequest = { expandedFormato = !expandedFormato }
        ) {
            DropdownMenuItem(
                text = { Text("DVD") },
                onClick = { formato = 0; expandedFormato = !expandedFormato }
            )
            DropdownMenuItem(
                text = { Text("Blu-ray") },
                onClick = { formato = 1; expandedFormato = !expandedFormato }
            )
            DropdownMenuItem(
                text = { Text("Online") },
                onClick = { formato = 2; expandedFormato = !expandedFormato }
            )
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
                        film.title = titulo
                        film.director = director
                        film.year = anyo
                        film.imdbUrl = if (url.isEmpty()) null else url
                        film.comments = comentarios
                        film.image = imagen
                        film.genre = genero
                        film.format = formato
                        viewModel.addFilm(film)
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
                    navController.popBackStack()
                }) {
                Text("Cancelar")
            }
        }
    }
}
