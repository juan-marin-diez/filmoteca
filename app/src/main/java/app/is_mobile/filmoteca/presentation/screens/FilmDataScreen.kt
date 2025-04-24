package app.is_mobile.filmoteca.presentation.screens
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import app.is_mobile.filmoteca.R
import app.is_mobile.filmoteca.presentation.navigation.Screens
import app.is_mobile.filmoteca.presentation.viewmodel.FilmViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FilmDataScreen(navController: NavHostController, indexOfFilm: Int, viewModel: FilmViewModel) {
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
    ) { innerPadding ->
        FilmDataScreenContent(navController = navController, innerPadding, indexOfFilm, viewModel) }
}

@Composable
fun FilmDataScreenContent(
    navController: NavHostController,
    innerPadding: PaddingValues,
    indexOfFilm: Int,
    viewModel: FilmViewModel
) {
    when(navController.currentBackStackEntry?.savedStateHandle?.get<String>(key = "key_result"))
    {
        "RESULT_OK" -> { Log.i("EDIT_FILM", "RESULT_OK") }
        "RESULT_ERROR" -> { Log.e("EDIT_FILM", "RESULT_ERROR") }
        "RESULT_CANCELED" -> { Log.i("EDIT_FILM", "RESULT_CANCELED") }
    }
    val film =viewModel.films.collectAsState().value[indexOfFilm]
    val context = LocalContext.current
    Column (modifier = Modifier.fillMaxSize()
        .padding(innerPadding)) {
        Row (verticalAlignment = Alignment.CenterVertically) {
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
            Column {
                Text(text = film.title?.let { it }?:"", style = MaterialTheme.typography.titleMedium)
                Text(text = "Director:", fontWeight = FontWeight.Bold)
                Text(film.director?.let { it }?:"")
                Text("Año:", fontWeight = FontWeight.Bold)
                Text(film.year.toString())
                if(film.genre>0 && film.format>0)
                    Text(context.resources.getStringArray(R.array.genero_list)[film.genre] + " - " +
                            context.resources.getStringArray(R.array.formato_list)[film.format])
                else
                    Text(context.resources.getStringArray(R.array.genero_list)[film.genre] +
                            context.resources.getStringArray(R.array.formato_list)[film.format])
            }
        }
        Button(modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 5.dp), onClick = { abrirPaginaWeb(film.imdbUrl?.let { it }?:"https://www.imdb.com/",
            context = context
        ) }) {
            Text("VER EN imdb")
        }
        Text(text = film.comments?.let { it }?:"", style = MaterialTheme.typography.bodyMedium)
        Row(Modifier.fillMaxWidth()
            .padding(horizontal = 5.dp),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(modifier = Modifier.fillMaxWidth(0.5f), onClick = { navController.navigateUp() }) {
                Text(stringResource(R.string.go_back))
            }
            Button(modifier = Modifier.fillMaxWidth()
                .padding(start = 5.dp), onClick = { navController.navigate(Screens.FilmEdit.route + "/${indexOfFilm}") }) {
                Text(stringResource(R.string.editar_pel_cula))
            }
        }
    }
}