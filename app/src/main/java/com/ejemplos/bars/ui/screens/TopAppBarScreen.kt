package com.ejemplos.bars.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.ejemplos.bars.ui.components.ContenidoDeEjemplo
import com.ejemplos.bars.ui.components.Elemento
import com.ejemplos.bars.ui.components.mostrarMensaje
import com.ejemplos.bars.ui.theme.BarsTheme
import com.ejemplos.bars.ui.theme.ColorTopAppBar

// =====================================================================
// TopAppBarScreen.kt
// ---------------------------------------------------------------------
// Ejemplo de TopAppBar: la barra superior "estándar".
//   - Título alineado a la izquierda.
//   - Botón de navegación (flecha atrás) a la izquierda.
//   - Botones de acción a la derecha, incluido un menú de 3 puntos.
// Tema del ejemplo: una bandeja de correo.
// =====================================================================

/**
 * Pantalla de ejemplo de TopAppBar.
 *
 * @param alVolver se llama al tocar la flecha para volver al menú.
 */
@OptIn(ExperimentalMaterial3Api::class) // Las barras de Material 3 aún son "experimentales"
@Composable
fun TopAppBarScreen(alVolver: () -> Unit) {
    val context = LocalContext.current

    // Estado del menú desplegable (3 puntos): abierto o cerrado.
    var menuAbierto by remember { mutableStateOf(false) }

    // "Pinned" = la barra queda FIJA arriba mientras se desplaza el contenido.
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        // nestedScroll conecta el desplazamiento de la lista con la barra.
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                // --- Título ---
                title = { Text("Bandeja de entrada") },

                // --- Ícono de navegación (izquierda) ---
                navigationIcon = {
                    IconButton(onClick = alVolver) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },

                // --- Acciones (derecha) ---
                actions = {
                    IconButton(onClick = { mostrarMensaje(context, "Botón: Buscar") }) {
                        Icon(Icons.Filled.Search, contentDescription = "Buscar")
                    }
                    IconButton(onClick = { mostrarMensaje(context, "Botón: Favoritos") }) {
                        Icon(Icons.Filled.Favorite, contentDescription = "Favoritos")
                    }

                    // Botón de 3 puntos que abre un menú con más opciones.
                    // El Box sirve para que el menú aparezca junto al botón.
                    Box {
                        IconButton(onClick = { menuAbierto = true }) {
                            Icon(Icons.Filled.MoreVert, contentDescription = "Más opciones")
                        }
                        DropdownMenu(
                            expanded = menuAbierto,
                            onDismissRequest = { menuAbierto = false },
                        ) {
                            DropdownMenuItem(
                                text = { Text("Marcar todo como leído") },
                                leadingIcon = { Icon(Icons.Filled.Check, contentDescription = null) },
                                onClick = {
                                    menuAbierto = false
                                    mostrarMensaje(context, "Menú: Marcar todo como leído")
                                },
                            )
                            DropdownMenuItem(
                                text = { Text("Configuración") },
                                leadingIcon = { Icon(Icons.Filled.Settings, contentDescription = null) },
                                onClick = {
                                    menuAbierto = false
                                    mostrarMensaje(context, "Menú: Configuración")
                                },
                            )
                        }
                    }
                },

                // --- Colores de la barra ---
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = ColorTopAppBar,
                    scrolledContainerColor = ColorTopAppBar,
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
            color = ColorTopAppBar,
            explicacion = "Esto es una TopAppBar, la barra más común. Tiene el título a la " +
                "izquierda y las acciones a la derecha. Usa \"pinnedScrollBehavior\": " +
                "aunque desplaces la lista, la barra se queda fija arriba. " +
                "Prueba el menú de los 3 puntos.",
            elementos = correos,
        )
    }
}

/** Datos de ejemplo para la lista. */
private val correos = listOf(
    Elemento("Ana García", "¿Revisaste el proyecto de Compose?", Icons.Filled.Person),
    Elemento("Equipo Android", "Nueva versión de Material 3 disponible", Icons.Filled.Notifications),
    Elemento("Tienda en línea", "Tu pedido ya va en camino", Icons.Filled.ShoppingCart),
    Elemento("Carlos Pérez", "Te comparto el diseño de la app", Icons.Filled.Person),
    Elemento("Curso Kotlin", "Lección 5: funciones de orden superior", Icons.Filled.Star),
    Elemento("María López", "¡Felicitaciones por tu primera app!", Icons.Filled.Favorite),
    Elemento("Soporte", "Tu consulta fue respondida", Icons.Filled.Email),
    Elemento("Comunidad Dev", "Reunión de desarrolladores este viernes", Icons.Filled.Notifications),
    Elemento("Luis Torres", "Ideas para la próxima pantalla", Icons.Filled.Person),
    Elemento("Google Play", "Tu app fue publicada con éxito", Icons.Filled.Star),
    Elemento("Sofía Ramírez", "¿Almorzamos mañana?", Icons.Filled.Person),
    Elemento("Boletín semanal", "Novedades del mundo Android", Icons.Filled.Email),
)

@Preview(showBackground = true)
@Composable
private fun TopAppBarScreenPreview() {
    BarsTheme { TopAppBarScreen(alVolver = {}) }
}
