package com.campusdigitalfp.filmoteca.ui.screens

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.campusdigitalfp.filmoteca.R

@Composable
@Preview
fun AboutScreen()
{
    Column {
        Text(text = "Creado por Juan Marín Díez")
        Image(
            painter = painterResource(id = R.drawable.foto),
            contentDescription = "Fotografía del autor"
        )
    }

}
