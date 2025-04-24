package app.is_mobile.filmoteca.presentation.screens

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import app.is_mobile.filmoteca.R
import app.is_mobile.filmoteca.domain.model.Film
import app.is_mobile.filmoteca.presentation.navigation.Screens
import app.is_mobile.filmoteca.presentation.viewmodel.FilmViewModel

@Composable
fun FilmListScreen(navController: NavHostController, viewModel: FilmViewModel = FilmViewModel()) {
    val isActionMode = remember { mutableStateOf(false) }
    val selectedFilms = viewModel.selectedFilms
    val films by viewModel.films.collectAsState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppBar(
                showNavigationButton = false,
                showMenuButton = true,
                navController = navController,
                showDelButton = isActionMode,
                viewModel = viewModel
            )
        },
    ) { innerPadding ->
        FilmListScreenContent(navController, innerPadding, isActionMode, selectedFilms, films, viewModel) }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FilmListScreenContent (
    navController: NavHostController,
    innerPadding: PaddingValues,
    isActionMode: MutableState<Boolean>,
    selectedFilms: List<Film>,
    films: List<Film>,
    viewModel: FilmViewModel
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
        items(films)
        { film ->
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.combinedClickable(
                    onClick = { if( !isActionMode.value )
                                    navController.navigate(Screens.FilmData.route + "/${films.indexOf(film)}")
                                else {
                                    if(selectedFilms.size==1&&selectedFilms.contains(film)) {
                                        viewModel.emptyFilmsToDelete()
                                        isActionMode.value = !isActionMode.value
                                    }
                                    else  if(selectedFilms.contains(film))
                                        viewModel.removeFilmToDelete(film)
                                    else
                                        viewModel.addFilmToDelete(film)
                                }
                              },
                    onClickLabel = "Ver datos de ${film.title}",
                    onLongClick = { if( !isActionMode.value ) {
                                        isActionMode.value = !isActionMode.value
                                        viewModel.addFilmToDelete(film)
                                    }
                                    else {
                                        viewModel.emptyFilmsToDelete()
                                        isActionMode.value = !isActionMode.value
                                    }
                                  }
                )
            ) {
                Box(modifier = Modifier.padding(5.dp)
                    .fillMaxWidth(0.25f))
                {
                    if(film.image!=null)
                        Image(
                            bitmap = film.image!!.asImageBitmap(),
                            contentDescription = "Cartel de ${film.title}",
                        )
                    else
                        Image(
                            painter = painterResource(id = R.drawable.filmoteca),
                            contentDescription = "Cartel de ${film.title}",
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
