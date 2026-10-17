package com.ejemplos.bars.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
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
import com.ejemplos.bars.ui.theme.ColorLargeTopAppBar

// =====================================================================
// LargeTopAppBarScreen.kt
// ---------------------------------------------------------------------
// Ejemplo de LargeTopAppBar: la barra más grande de Material 3.
// Muestra un título de gran tamaño que llama la atención. Al desplazar
// se contrae hasta quedar como una TopAppBar normal.
// Tema del ejemplo: una app de viajes.
// =====================================================================

/**
 * Pantalla de ejemplo de LargeTopAppBar.
 *
 * @param alVolver se llama al tocar la flecha para volver al menú.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LargeTopAppBarScreen(alVolver: () -> Unit) {
    val context = LocalContext.current

    // Igual que en MediumTopAppBar: se contrae al bajar y se expande al subir.
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = { Text("Mis viajes") },
                navigationIcon = {
                    IconButton(onClick = alVolver) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                actions = {
                    IconButton(onClick = { mostrarMensaje(context, "Botón: Nuevo viaje") }) {
                        Icon(Icons.Filled.Add, contentDescription = "Nuevo viaje")
                    }
                    IconButton(onClick = { mostrarMensaje(context, "Botón: Editar") }) {
                        Icon(Icons.Filled.Edit, contentDescription = "Editar")
                    }
                    IconButton(onClick = { mostrarMensaje(context, "Botón: Calendario") }) {
                        Icon(Icons.Filled.DateRange, contentDescription = "Calendario")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = ColorLargeTopAppBar,
                    scrolledContainerColor = ColorLargeTopAppBar,
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
            color = ColorLargeTopAppBar,
            explicacion = "Esto es una LargeTopAppBar, la más grande de todas. Su título " +
                "enorme es perfecto para destacar una sección. Desplaza la lista y mira " +
                "cómo se transforma poco a poco en una barra pequeña.",
            elementos = destinos,
        )
    }
}

/** Datos de ejemplo para la lista. */
private val destinos = listOf(
    Elemento("Torres del Paine", "Chile · 5 días de trekking", Icons.Filled.Place),
    Elemento("Machu Picchu", "Perú · Ruta del Inca", Icons.Filled.LocationOn),
    Elemento("Cartagena", "Colombia · Ciudad amurallada", Icons.Filled.Place),
    Elemento("Tulum", "México · Playas y ruinas mayas", Icons.Filled.LocationOn),
    Elemento("Salar de Uyuni", "Bolivia · El espejo del cielo", Icons.Filled.Place),
    Elemento("Cataratas del Iguazú", "Argentina / Brasil", Icons.Filled.LocationOn),
    Elemento("Barcelona", "España · Arte y playa", Icons.Filled.Place),
    Elemento("Isla de Pascua", "Chile · Los moáis", Icons.Filled.LocationOn),
    Elemento("Galápagos", "Ecuador · Naturaleza única", Icons.Filled.Place),
    Elemento("Montevideo", "Uruguay · Rambla y tango", Icons.Filled.LocationOn),
    Elemento("San Pedro de Atacama", "Chile · Desierto y estrellas", Icons.Filled.Place),
    Elemento("Ciudad de Panamá", "Panamá · El canal", Icons.Filled.LocationOn),
)

@Preview(showBackground = true)
@Composable
private fun LargeTopAppBarScreenPreview() {
    BarsTheme { LargeTopAppBarScreen(alVolver = {}) }
}
