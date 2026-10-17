package com.ejemplos.bars.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ejemplos.bars.Pantalla
import com.ejemplos.bars.ui.theme.BarsTheme
import com.ejemplos.bars.ui.theme.ColorCenterAlignedTopAppBar
import com.ejemplos.bars.ui.theme.ColorLargeTopAppBar
import com.ejemplos.bars.ui.theme.ColorMediumTopAppBar
import com.ejemplos.bars.ui.theme.ColorTopAppBar

// =====================================================================
// MenuScreen.kt
// ---------------------------------------------------------------------
// Pantalla principal: un menú centrado con una tarjeta por cada barra.
// Al tocar una tarjeta se avisa a MainActivity qué pantalla abrir.
// =====================================================================

/**
 * Menú principal.
 *
 * @param alElegir función que se llama con la pantalla elegida.
 */
@Composable
fun MenuScreen(alElegir: (Pantalla) -> Unit) {
    // Scaffold es el "esqueleto" de una pantalla Material; aquí solo
    // lo usamos para obtener el color de fondo y los márgenes seguros.
    Scaffold { paddingInterno ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingInterno)
                .verticalScroll(rememberScrollState()) // Por si la pantalla es pequeña
                .padding(24.dp),
            // Centramos todo vertical y horizontalmente.
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // ---- Encabezado ----
            Text(
                text = "Barras superiores",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = "Material 3 con Jetpack Compose",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Elige una barra para verla en acción. " +
                    "¡Desplaza la lista de cada ejemplo y observa cómo se comporta!",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
            )
            Spacer(Modifier.height(24.dp))

            // ---- Las 4 opciones del menú ----
            // widthIn limita el ancho en tablets para que se vea ordenado.
            Column(
                modifier = Modifier.widthIn(max = 480.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                OpcionMenu(
                    nombre = "TopAppBar",
                    descripcion = "La barra clásica: título a la izquierda y acciones a la derecha.",
                    icono = Icons.Filled.Email,
                    color = ColorTopAppBar,
                    alTocar = { alElegir(Pantalla.TOP_APP_BAR) },
                )
                OpcionMenu(
                    nombre = "CenterAlignedTopAppBar",
                    descripcion = "Título centrado. Ideal para pantallas principales.",
                    icono = Icons.Filled.PlayArrow,
                    color = ColorCenterAlignedTopAppBar,
                    alTocar = { alElegir(Pantalla.CENTER_ALIGNED) },
                )
                OpcionMenu(
                    nombre = "MediumTopAppBar",
                    descripcion = "Más alta, con el título abajo. Se encoge al desplazar.",
                    icono = Icons.Filled.Star,
                    color = ColorMediumTopAppBar,
                    alTocar = { alElegir(Pantalla.MEDIUM) },
                )
                OpcionMenu(
                    nombre = "LargeTopAppBar",
                    descripcion = "La más grande y llamativa. Título enorme que se contrae.",
                    icono = Icons.Filled.Place,
                    color = ColorLargeTopAppBar,
                    alTocar = { alElegir(Pantalla.LARGE) },
                )
            }
        }
    }
}

/**
 * Una tarjeta del menú: círculo de color con ícono, nombre y descripción.
 */
@Composable
private fun OpcionMenu(
    nombre: String,
    descripcion: String,
    icono: ImageVector,
    color: Color,
    alTocar: () -> Unit,
) {
    // ElevatedCard con onClick ya se comporta como un botón (con efecto al tocar).
    ElevatedCard(
        onClick = alTocar,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Círculo de color con el ícono blanco en el centro.
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .background(color = color, shape = CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Icon(icono, contentDescription = null, tint = Color.White)
            }

            // weight(1f) hace que el texto ocupe todo el espacio sobrante.
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
            ) {
                Text(
                    text = nombre,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = color,
                )
                Text(text = descripcion, style = MaterialTheme.typography.bodySmall)
            }

            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null)
        }
    }
}

/** Vista previa para Android Studio (pestaña "Split" o "Design"). */
@Preview(showBackground = true)
@Composable
private fun MenuScreenPreview() {
    BarsTheme { MenuScreen(alElegir = {}) }
}
