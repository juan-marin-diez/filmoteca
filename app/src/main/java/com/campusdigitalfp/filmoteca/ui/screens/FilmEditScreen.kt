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
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FilmEditScreen(navController: NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppBar(showNavigationButton = true, navController = navController)
        },
    ) { FilmEditScreenContent(navController) }
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