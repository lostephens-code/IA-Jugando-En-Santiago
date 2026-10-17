package com.ejemplos.bars

// =====================================================================
// Pantalla.kt
// ---------------------------------------------------------------------
// Lista de TODAS las pantallas que existen en la app.
// Usar un "enum" evita errores de escritura: en vez de navegar con
// textos como "large", usamos Pantalla.LARGE y el compilador nos ayuda.
// =====================================================================

enum class Pantalla {
    MENU,            // Menú principal con los 4 botones
    TOP_APP_BAR,     // Ejemplo de TopAppBar
    CENTER_ALIGNED,  // Ejemplo de CenterAlignedTopAppBar
    MEDIUM,          // Ejemplo de MediumTopAppBar
    LARGE,           // Ejemplo de LargeTopAppBar
}
