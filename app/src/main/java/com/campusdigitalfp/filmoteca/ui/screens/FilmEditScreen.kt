package com.campusdigitalfp.filmoteca.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

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