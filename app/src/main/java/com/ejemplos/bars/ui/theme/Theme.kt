package com.ejemplos.bars.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// =====================================================================
// Theme.kt
// ---------------------------------------------------------------------
// Define el tema Material 3 de la app. Todo lo que se dibuje dentro de
// BarsTheme { ... } usará estos colores y la tipografía por defecto.
// =====================================================================

/** Colores para el modo claro. */
private val EsquemaClaro = lightColorScheme(
    primary = Violeta,
    primaryContainer = VioletaClaro,
    onPrimaryContainer = VioletaOscuro,
)

/** Colores para el modo oscuro. */
private val EsquemaOscuro = darkColorScheme(
    primary = Lavanda,
    primaryContainer = Violeta,
    onPrimaryContainer = VioletaClaro,
)

/**
 * Tema de la aplicación.
 *
 * @param modoOscuro si es true se usan los colores oscuros. Por defecto
 *                   sigue la configuración del teléfono.
 * @param contenido  la interfaz que tendrá este tema aplicado.
 */
@Composable
fun BarsTheme(
    modoOscuro: Boolean = isSystemInDarkTheme(),
    contenido: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (modoOscuro) EsquemaOscuro else EsquemaClaro,
        content = contenido,
    )
}
