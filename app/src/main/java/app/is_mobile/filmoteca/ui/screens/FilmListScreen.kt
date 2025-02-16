package app.is_mobile.filmoteca.ui.screens

import android.util.Log.e
import android.util.Log.i
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import app.is_mobile.filmoteca.data.Film
import app.is_mobile.filmoteca.data.FilmDataSource
import app.is_mobile.filmoteca.navigation.Screens

@Composable
fun FilmListScreen(navController: NavHostController) {
    val isActionMode = remember { mutableStateOf(false) }
    val selectedFilms = remember { mutableStateListOf<Film>() }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppBar(
                showNavigationButton = false,
                showMenuButton = true,
                navController = navController,
                showDelButton = isActionMode,
                selectedFilms = selectedFilms
            )
        },
    ) { innerPadding ->
        FilmListScreenContent(navController, innerPadding, isActionMode, selectedFilms) }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FilmListScreenContent (
    navController: NavHostController,
    innerPadding: PaddingValues,
    isActionMode: MutableState<Boolean>,
    selectedFilms: SnapshotStateList<Film>
) {
    when(navController.currentBackStackEntry?.savedStateHandle?.get<String>(key = "key_result"))
    {
        "RESULT_OK" -> { i("ADD_NEW_FILM", "RESULT_OK") }
        "RESULT_ERROR" -> { e("ADD_NEW_FILM", "RESULT_ERROR") }
        "RESULT_CANCELED" -> { i("ADD_NEW_FILM", "RESULT_CANCELED") }
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize()
            .padding(innerPadding),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        items(items = FilmDataSource.films)
        { film ->
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.combinedClickable(
                    onClick = { if( !isActionMode.value )
                                    navController.navigate(Screens.FilmData.route + "/${FilmDataSource.films.indexOf(film)}")
                                else {
                                    if(selectedFilms.contains(film))
                                        selectedFilms.remove(film)
                                    else
                                        selectedFilms.add(film)
                                }
                              },
                    onClickLabel = "Ver datos de ${film.title}",
                    onLongClick = { if( !isActionMode.value ) {
                                        isActionMode.value = !isActionMode.value
                                        selectedFilms.add(film)
                                    }
                                    else {
                                        selectedFilms.clear()
                                        isActionMode.value = !isActionMode.value
                                    }
                                  }
                )
            ) {
                Box(modifier = Modifier.padding(5.dp)
                    .fillMaxWidth(0.25f))
                {
                    Image(
                        painter = painterResource(id = film.imageResId),
                        contentDescription = "Cartel de ${film.title}"
                    )
                    if(selectedFilms.contains(film))
                        Icon(
                            modifier = Modifier.align(Alignment.TopEnd),
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = "Seleccionada",
                            tint = Color.White
                        )
                }
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
    FilmListScreenContent(
        navController = NavHostController(LocalContext.current),
        innerPadding = PaddingValues(5.dp),
        isActionMode = remember { mutableStateOf(false) },
        selectedFilms = remember { mutableStateListOf() }
    )
}
