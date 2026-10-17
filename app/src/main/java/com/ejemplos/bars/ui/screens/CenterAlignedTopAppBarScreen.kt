package com.ejemplos.bars.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.ejemplos.bars.ui.theme.ColorCenterAlignedTopAppBar

// =====================================================================
// CenterAlignedTopAppBarScreen.kt
// ---------------------------------------------------------------------
// Ejemplo de CenterAlignedTopAppBar: igual que TopAppBar, pero con el
// título CENTRADO. Suele usarse en la pantalla principal de una app.
// Tema del ejemplo: una app de música.
// =====================================================================

/**
 * Pantalla de ejemplo de CenterAlignedTopAppBar.
 *
 * @param alVolver se llama al tocar la flecha para volver al menú.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterAlignedTopAppBarScreen(alVolver: () -> Unit) {
    val context = LocalContext.current

    // "Enter always" = la barra se ESCONDE al bajar por la lista y
    // REAPARECE apenas subes un poco. Así el contenido gana espacio.
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Mi música") },
                navigationIcon = {
                    IconButton(onClick = alVolver) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                // En barras centradas se recomienda UNA sola acción para mantener el equilibrio.
                actions = {
                    IconButton(onClick = { mostrarMensaje(context, "Botón: Mi perfil") }) {
                        Icon(Icons.Filled.AccountCircle, contentDescription = "Mi perfil")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = ColorCenterAlignedTopAppBar,
                    scrolledContainerColor = ColorCenterAlignedTopAppBar,
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
            color = ColorCenterAlignedTopAppBar,
            explicacion = "Esto es una CenterAlignedTopAppBar: el título aparece centrado. " +
                "Usa \"enterAlwaysScrollBehavior\": desplaza la lista hacia abajo y la " +
                "barra se esconde; sube un poco y vuelve a aparecer.",
            elementos = canciones,
        )
    }
}

/** Datos de ejemplo para la lista. */
private val canciones = listOf(
    Elemento("Código en la noche", "Los Compilados · 3:45", Icons.Filled.PlayArrow),
    Elemento("Kotlin Blues", "Banda Lambda · 4:12", Icons.Filled.Favorite),
    Elemento("Recomposición", "DJ Compose · 3:28", Icons.Filled.PlayArrow),
    Elemento("Estado mutable", "Los Remember · 2:59", Icons.Filled.Star),
    Elemento("Activity de mi vida", "Intent Records · 3:33", Icons.Filled.PlayArrow),
    Elemento("Material You", "Dinámica Color · 4:01", Icons.Filled.Favorite),
    Elemento("Gradle tarda", "Build Time · 5:20", Icons.Filled.PlayArrow),
    Elemento("Null Safety", "Los Seguros · 3:10", Icons.Filled.Star),
    Elemento("Corrutina de amor", "Suspend Band · 3:47", Icons.Filled.PlayArrow),
    Elemento("Oreo 8.0", "API 26 · 2:48", Icons.Filled.Favorite),
    Elemento("LazyColumn", "Scroll Infinito · 4:30", Icons.Filled.PlayArrow),
    Elemento("Hola Mundo", "Primer Proyecto · 1:59", Icons.Filled.Star),
)

@Preview(showBackground = true)
@Composable
private fun CenterAlignedTopAppBarScreenPreview() {
    BarsTheme { CenterAlignedTopAppBarScreen(alVolver = {}) }
}
