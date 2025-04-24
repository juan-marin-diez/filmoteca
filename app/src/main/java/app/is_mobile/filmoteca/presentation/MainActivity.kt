package app.is_mobile.filmoteca.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModel
import app.is_mobile.filmoteca.presentation.navigation.Navigation
import app.is_mobile.filmoteca.presentation.viewmodel.FilmViewModel
import app.is_mobile.filmoteca.ui.theme.FilmotecaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: ViewModel = FilmViewModel()
            Navigation(viewModel = viewModel as FilmViewModel)
        }
    }
}
