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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.campusdigitalfp.filmoteca.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FilmEditScreen(navController: NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppBar(showNavigationButton = true, navController = navController)
        },
    ) { innerPadding ->
        NewFilmEditScreenContent(navController = navController, innerPadding) }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewFilmEditScreenContent(navController: NavHostController, innerPadding: PaddingValues) {
    var titulo by remember { mutableStateOf("") }
    var director: String by remember { mutableStateOf("") }
    var anyo: Int by remember { mutableIntStateOf(1997) }
    var url by remember { mutableStateOf("") }
    var imagen by remember { mutableIntStateOf(1) }
    var comentarios by remember { mutableStateOf("") }

    var expandedGenero by remember { mutableStateOf(false) }
    var expandedFormato by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val generoList = context.resources.getStringArray(R.array.genero_list).toList()
    val formatoList = context.resources.getStringArray(R.array.formato_list).toList()


    var genero by remember { mutableIntStateOf(0) }
    var formato by remember { mutableIntStateOf(1) }
    Column (
        modifier = Modifier.fillMaxSize(),
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
                contentDescription = "Palomitas"
            )
            Button(
                modifier = Modifier
                    .padding(start = 5.dp)
                    .fillMaxWidth(0.5f),
                onClick = { TODO() }) {
                Text("Capturar fotografía")
            }
            Button(
                modifier = Modifier
                    .padding(start = 5.dp)
                    .fillMaxWidth(),
                onClick = { TODO() }) {
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
            value = "",
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
        DropdownMenu(
            expanded = expandedGenero,
            onDismissRequest = { expandedGenero = !expandedGenero }
        ) {
            DropdownMenuItem(
                text = { Text("Acción") },
                onClick = { TODO() }
            )
            DropdownMenuItem(
                text = { Text("Drama") },
                onClick = { TODO() }
            )
            DropdownMenuItem(
                text = { Text("Comedia") },
                onClick = { TODO() }
            )
            DropdownMenuItem(
                text = { Text("Terror") },
                onClick = { TODO() }
            )
            DropdownMenuItem(
                text = { Text("Sci-Fi") },
                onClick = { TODO() }
            )
        }
        DropdownMenu(
            expanded = expandedFormato,
            onDismissRequest = { expandedFormato = !expandedFormato }
        ) {
            DropdownMenuItem(
                text = { Text("DVD") },
                onClick = { TODO() }
            )
            DropdownMenuItem(
                text = { Text("Blu-ray") },
                onClick = { TODO() }
            )
            DropdownMenuItem(
                text = { Text("Online") },
                onClick = { TODO() }
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
    }
}

@Composable
@Preview
fun NewFilmEditScreenContentPreview() {
    NewFilmEditScreenContent(navController = NavHostController(context = LocalContext.current), innerPadding = PaddingValues(0.dp))
}