package app.is_mobile.filmoteca.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.R
import app.is_mobile.filmoteca.data.DelFilm
import app.is_mobile.filmoteca.data.Film
import app.is_mobile.filmoteca.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    showNavigationButton: Boolean,
    showMenuButton: Boolean,
    navController: NavHostController,
    showDelButton: MutableState<Boolean>? = null,
    selectedFilms: SnapshotStateList<Film>? = null
)
{
    val context = LocalContext.current
    val visible: MutableState<Boolean> = remember { mutableStateOf(false) }
    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        navigationIcon = {
            if (showNavigationButton) {
                Row {
                    IconButton(onClick = { navController.navigate(Screens.FilmList.route) }) {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = stringResource(R.string.inicio)
                        )
                    }
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.go_back)
                        )
                    }
                }
            }
        },
        actions = {
            if (showMenuButton)
                IconButton(onClick = { visible.value = !visible.value }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = context.resources.getString(R.string.menu)
                    )
                }
            if (showDelButton?.value?:false)
                IconButton(onClick = {
                    if (selectedFilms != null && showDelButton != null) {
                        DelFilm(selectedFilms)
                        showDelButton?.let { it.value = !it.value }
                        navController.navigate(Screens.FilmList.route)
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Borrar"
                    )
                }
            Menu(navController = navController,visible = visible)
        }
    )
}


@Composable
fun Menu(navController: NavHostController, visible: MutableState<Boolean>)
{
    val context = LocalContext.current
    DropdownMenu(expanded = visible.value, onDismissRequest = { visible.value = !visible.value }) {
        DropdownMenuItem(
            onClick = { visible.value=false; navController.navigate(Screens.FilmAdd.route) },
            text = { Text(context.resources.getString(R.string.add_film)) }
        )
        DropdownMenuItem(
            onClick = { visible.value = false; navController.navigate(Screens.About.route) },
            text = { Text(context.resources.getString(R.string.acerca_de)) }
        )
    }
}

fun abrirPaginaWeb(url: String, context: Context) {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url) // Establece la URL que quieres abrir
    }
    context.startActivity(intent) // Inicia la actividad
}
