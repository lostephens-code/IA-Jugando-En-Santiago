package com.ejemplos.bars.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.ejemplos.bars.ui.components.ContenidoDeEjemplo
import com.ejemplos.bars.ui.components.Elemento
import com.ejemplos.bars.ui.components.mostrarMensaje
import com.ejemplos.bars.ui.theme.BarsTheme
import com.ejemplos.bars.ui.theme.ColorMediumTopAppBar

// =====================================================================
// MediumTopAppBarScreen.kt
// ---------------------------------------------------------------------
// Ejemplo de MediumTopAppBar: una barra más alta que la normal, con el
// título en una segunda línea. Al desplazar, se ENCOGE hasta quedar
// del tamaño de una TopAppBar normal.
// Tema del ejemplo: un recetario.
// =====================================================================

/**
 * Pantalla de ejemplo de MediumTopAppBar.
 *
 * @param alVolver se llama al tocar la flecha para volver al menú.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediumTopAppBarScreen(alVolver: () -> Unit) {
    val context = LocalContext.current

    // "Exit until collapsed" = al bajar, la barra se contrae hasta su tamaño
    // mínimo (pero no desaparece). Al volver arriba, se expande de nuevo.
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MediumTopAppBar(
                title = { Text("Recetas favoritas") },
                navigationIcon = {
                    IconButton(onClick = alVolver) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                actions = {
                    IconButton(onClick = { mostrarMensaje(context, "Botón: Buscar receta") }) {
                        Icon(Icons.Filled.Search, contentDescription = "Buscar receta")
                    }
                    IconButton(onClick = { mostrarMensaje(context, "Botón: Compartir") }) {
                        Icon(Icons.Filled.Share, contentDescription = "Compartir")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = ColorMediumTopAppBar,
                    scrolledContainerColor = ColorMediumTopAppBar,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White,
                ),
                scrollBehavior = scrollBehavior,
            )
        },
    ) { paddingInterno ->
        ContenidoDeEjemplo(
            paddingInterno = paddingInterno,
            color = ColorMediumTopAppBar,
            explicacion = "Esto es una MediumTopAppBar. Es más alta y el título va debajo " +
                "de los íconos. Usa \"exitUntilCollapsedScrollBehavior\": desplaza la " +
                "lista y verás cómo el título sube y la barra se encoge.",
            elementos = recetas,
        )
    }
}

/** Datos de ejemplo para la lista. */
private val recetas = listOf(
    Elemento("Empanadas de pino", "Tiempo: 90 min · Dificultad media", Icons.Filled.Star),
    Elemento("Pastel de choclo", "Tiempo: 75 min · Dificultad media", Icons.Filled.Favorite),
    Elemento("Tacos al pastor", "Tiempo: 60 min · Fácil", Icons.Filled.ThumbUp),
    Elemento("Arepas rellenas", "Tiempo: 40 min · Fácil", Icons.Filled.Star),
    Elemento("Ceviche peruano", "Tiempo: 30 min · Fácil", Icons.Filled.Favorite),
    Elemento("Paella valenciana", "Tiempo: 80 min · Difícil", Icons.Filled.ThumbUp),
    Elemento("Sopaipillas", "Tiempo: 45 min · Fácil", Icons.Filled.Star),
    Elemento("Lasaña casera", "Tiempo: 100 min · Media", Icons.Filled.Favorite),
    Elemento("Guacamole", "Tiempo: 10 min · Muy fácil", Icons.Filled.ThumbUp),
    Elemento("Brownies", "Tiempo: 50 min · Fácil", Icons.Filled.Star),
    Elemento("Churros", "Tiempo: 35 min · Media", Icons.Filled.Favorite),
    Elemento("Alfajores", "Tiempo: 60 min · Media", Icons.Filled.ThumbUp),
)

@Preview(showBackground = true)
@Composable
private fun MediumTopAppBarScreenPreview() {
    BarsTheme { MediumTopAppBarScreen(alVolver = {}) }
}
