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
import com.ejemplos.bars.database.AppDatabase
import com.ejemplos.bars.ui.components.AuthViewModel
import com.ejemplos.bars.ui.screens.CenterAlignedTopAppBarScreen
import com.ejemplos.bars.ui.screens.LargeTopAppBarScreen
import com.ejemplos.bars.ui.screens.LoginScreen
import com.ejemplos.bars.ui.screens.MediumTopAppBarScreen
import com.ejemplos.bars.ui.screens.MenuScreen
import com.ejemplos.bars.ui.screens.RegisterScreen
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

        //Inicializar BD
        val database = AppDatabase.getDatabase(applicationContext)
        val authViewModel = AuthViewModel(database.userDao())

        // Dibuja la app de borde a borde (detrás de la barra de estado).
        // Las barras superiores de Material 3 ya dejan el espacio necesario.
        enableEdgeToEdge()

        // setContent reemplaza al clásico setContentView(R.layout...):
        // aquí dentro describimos la interfaz con Compose.
        setContent {
            BarsTheme {
                AuthAppRoot(viewModel = authViewModel)
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
fun AppBars(viewModel: AuthViewModel, onLogout: () -> Unit) {
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
        Pantalla.MENU -> MenuScreen(alElegir = { pantallaActual = it },
            alCerrarSesion = {
                viewModel.clearMessage()
                onLogout() // Vuelve a la pantalla de Login
            }
        )
        Pantalla.TOP_APP_BAR -> TopAppBarScreen(alVolver = volverAlMenu)
        Pantalla.CENTER_ALIGNED -> CenterAlignedTopAppBarScreen(alVolver = volverAlMenu)
        Pantalla.MEDIUM -> MediumTopAppBarScreen(alVolver = volverAlMenu)
        Pantalla.LARGE -> LargeTopAppBarScreen(alVolver = volverAlMenu)
    }
}
enum class AuthScreen {
    LOGIN,
    REGISTER,
    HOME
}

@Composable
fun AuthAppRoot(viewModel: AuthViewModel) {
    // Estado para controlar qué pantalla se muestra
    var currentScreen by rememberSaveable { mutableStateOf(AuthScreen.LOGIN) }

    // Control opcional para el botón "Atrás" del sistema
    BackHandler(enabled = currentScreen != AuthScreen.LOGIN) {
        if (currentScreen == AuthScreen.REGISTER || currentScreen == AuthScreen.HOME) {
            currentScreen = AuthScreen.LOGIN
            viewModel.clearMessage()
        }
    }

    // Navegación basada en estados
    when (currentScreen) {
        AuthScreen.LOGIN -> LoginScreen(
            viewModel = viewModel,
            onNavigateToRegister = {
                viewModel.clearMessage()
                currentScreen = AuthScreen.REGISTER
            },
            onLoginSuccess = {
                currentScreen = AuthScreen.HOME
            }
        )
        AuthScreen.REGISTER -> RegisterScreen(
            viewModel = viewModel,
            onNavigateToLogin = {
                viewModel.clearMessage()
                currentScreen = AuthScreen.LOGIN
            }
        )
        AuthScreen.HOME -> AppBars(viewModel = viewModel,
            onLogout = {
                currentScreen = AuthScreen.LOGIN
            })
    }
}
