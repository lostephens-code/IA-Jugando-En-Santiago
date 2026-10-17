package com.ejemplos.bars.ui.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// =====================================================================
// Componentes.kt
// ---------------------------------------------------------------------
// Piezas pequeñas que se repiten en las 4 pantallas de ejemplo.
// Tenerlas en un solo lugar evita copiar y pegar código.
// =====================================================================

/**
 * Muestra un mensaje corto (Toast) en la parte inferior de la pantalla.
 * Lo usamos para saber qué botón se presionó.
 */
fun mostrarMensaje(context: Context, texto: String) {
    Toast.makeText(context, texto, Toast.LENGTH_SHORT).show()
}

/**
 * Un elemento de la lista de ejemplo.
 *
 * @param titulo    texto principal.
 * @param subtitulo texto secundario, más pequeño.
 * @param icono     ícono que aparece a la izquierda.
 */
data class Elemento(
    val titulo: String,
    val subtitulo: String,
    val icono: ImageVector,
)

/**
 * Contenido desplazable que se muestra debajo de cada barra.
 *
 * Tiene una tarjeta explicativa al inicio y luego una lista de elementos.
 * La lista es larga a propósito: al desplazarla se puede ver cómo
 * reacciona la barra superior (cambia de color, se encoge, se oculta...).
 *
 * @param paddingInterno espacio que entrega el Scaffold para no quedar
 *                       tapados por la barra superior.
 * @param color          color principal de la pantalla.
 * @param explicacion    texto que explica la barra de esta pantalla.
 * @param elementos      elementos que se muestran en la lista.
 */
@Composable
fun ContenidoDeEjemplo(
    paddingInterno: PaddingValues,
    color: Color,
    explicacion: String,
    elementos: List<Elemento>,
) {
    // Necesitamos el "context" para poder mostrar un Toast.
    val context = LocalContext.current

    // LazyColumn = lista eficiente: solo dibuja lo que se ve en pantalla.
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = paddingInterno,
    ) {
        // 1) Tarjeta que explica qué barra estamos viendo.
        item {
            TarjetaExplicativa(color = color, explicacion = explicacion)
        }

        // 2) Un ListItem por cada elemento de la lista.
        items(elementos) { elemento ->
            ListItem(
                headlineContent = { Text(elemento.titulo, fontWeight = FontWeight.SemiBold) },
                supportingContent = { Text(elemento.subtitulo) },
                leadingContent = { Icon(elemento.icono, contentDescription = null, tint = color) },
                trailingContent = {
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null)
                },
                modifier = Modifier.clickable {
                    mostrarMensaje(context, "Elegiste: ${elemento.titulo}")
                },
            )
        }
    }
}

/**
 * Tarjeta de color suave con el texto que explica la barra.
 */
@Composable
private fun TarjetaExplicativa(color: Color, explicacion: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        // copy(alpha = 0.12f) crea una versión casi transparente del color.
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.12f)),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = "¿Qué estoy viendo?",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = color,
            )
            Text(text = explicacion, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
