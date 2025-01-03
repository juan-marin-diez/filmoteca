package com.campusdigitalfp.filmoteca.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(showNavigationButton: Boolean, navController: NavHostController)
{
    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        navigationIcon = {
            if (showNavigationButton) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Go back"
                    )
                }
            }
        }
    )
}

