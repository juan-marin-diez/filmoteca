package com.campusdigitalfp.filmoteca.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.campusdigitalfp.filmoteca.R

@Composable
@Preview(showBackground = true)
fun AboutScreen() {
    val funtcionNotImplemented = stringResource(id = R.string.functionality_not_implemented)
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.created_by),
            modifier = Modifier.padding(5.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        )
        Image(
            painter = painterResource(id = R.drawable.foto),
            contentDescription = stringResource(id = R.string.created_by)
        )
        Row {
            Button(onClick = { abrirPaginaWeb("https://www.campusdigitalfp.com/", context) }) {
                Text(text = stringResource(id = R.string.go_to_website))
            }

            Button(onClick = { mandarEmail(context, email = "juan.marindiez@outlook.es", asunto = "Soporte filmoteca") }) {
                Text(text = stringResource(id = R.string.get_support))
            }
        }
        Button(onClick = { showToast(
            context = context,
            message = funtcionNotImplemented
        ) }) {
            Text(text = stringResource(id = R.string.go_back))
        }
    }
}


fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun abrirPaginaWeb(url: String, context: Context) {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url) // Establece la URL que quieres abrir
    }
    context.startActivity(intent) // Inicia la actividad
}

fun mandarEmail(context: Context, email: String, asunto: String) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:$email")
        putExtra(Intent.EXTRA_SUBJECT, asunto)
    }

    // Verifica si hay una aplicación que puede manejar el Intent
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    }
    else {
        showToast(context, "No hay ninguna aplicación de correo")
    }
}