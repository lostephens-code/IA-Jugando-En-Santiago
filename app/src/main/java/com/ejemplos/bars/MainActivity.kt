package com.ejemplos.bars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.ejemplos.bars.ui.screens.CenterAlignedTopAppBarScreen
import com.ejemplos.bars.ui.screens.LargeTopAppBarScreen
import com.ejemplos.bars.ui.screens.MediumTopAppBarScreen
import com.ejemplos.bars.ui.screens.MenuScreen
import com.ejemplos.bars.ui.screens.TopAppBarScreen
import com.ejemplos.bars.ui.theme.BarsTheme

// =====================================================================
// MainActivity.kt
// ---------------------------------------------------------------------
// Punto de entrada de la app. Solo hay UNA Activity; las distintas
// pantallas son funciones @Composable que se muestran u ocultan
// según el valor de la variable "pantallaActual".
// =====================================================================

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Dibuja la app de borde a borde (detrás de la barra de estado).
        // Las barras superiores de Material 3 ya dejan el espacio necesario.
        enableEdgeToEdge()

        // setContent reemplaza al clásico setContentView(R.layout...):
        // aquí dentro describimos la interfaz con Compose.
        setContent {
            BarsTheme {
                AppBars()
            }
        }
    }
}

/**
 * Navegación de la app.
 *
 * Guardamos en una variable de estado qué pantalla se está viendo.
 * Cuando esa variable cambia, Compose vuelve a dibujar y muestra
 * la pantalla nueva. ¡Así de simple es navegar sin librerías extra!
 */
@Composable
fun AppBars() {
    // rememberSaveable conserva el valor aunque se gire el teléfono.
    var pantallaActual by rememberSaveable { mutableStateOf(Pantalla.MENU) }

    // Función reutilizable para regresar al menú.
    val volverAlMenu = { pantallaActual = Pantalla.MENU }

    // Si NO estamos en el menú, el botón "Atrás" del teléfono vuelve al menú.
    // (En el menú, "Atrás" cierra la app como siempre.)
    BackHandler(enabled = pantallaActual != Pantalla.MENU) {
        volverAlMenu()
    }

    // Según la pantalla actual, mostramos la función correspondiente.
    when (pantallaActual) {
        Pantalla.MENU -> MenuScreen(alElegir = { pantallaActual = it })
        Pantalla.TOP_APP_BAR -> TopAppBarScreen(alVolver = volverAlMenu)
        Pantalla.CENTER_ALIGNED -> CenterAlignedTopAppBarScreen(alVolver = volverAlMenu)
        Pantalla.MEDIUM -> MediumTopAppBarScreen(alVolver = volverAlMenu)
        Pantalla.LARGE -> LargeTopAppBarScreen(alVolver = volverAlMenu)
    }
}
