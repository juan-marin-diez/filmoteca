package app.is_mobile.filmoteca

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import app.is_mobile.filmoteca.navigation.Navigation
import app.is_mobile.filmoteca.ui.theme.FilmotecaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FilmotecaTheme {
                Navigation()
            }
        }
    }
}
