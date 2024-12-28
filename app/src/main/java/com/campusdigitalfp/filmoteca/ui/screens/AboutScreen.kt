package com.campusdigitalfp.filmoteca.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.campusdigitalfp.filmoteca.R

@Composable
@Preview(showBackground = true)
fun AboutScreen() {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Creado por Juan Marín Díez",
            modifier = Modifier.padding(5.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        )
        Image(
            painter = painterResource(id = R.drawable.foto),
            contentDescription = "Fotografía del autor"
        )
        Row {
            Button(onClick = { showToast(
                context = context,
                message = "Funcionalidad sin implementar",
            ) }) {
                Text(text = "Ir al sitio web")
            }
            Button(onClick = { showToast(
                context = context,
                message = "Funcionalidad sin implementar"
            ) }) {
                Text(text = "Obtener soporte")
            }
        }
        Button(onClick = { showToast(
            context = context,
            message = "Funcionalidad sin implementar"
        ) }) {
            Text(text = "Volver")
        }
    }
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
