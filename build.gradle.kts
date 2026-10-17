// =====================================================================
// build.gradle.kts (nivel de PROYECTO)
// ---------------------------------------------------------------------
// Declara los plugins que usarán los módulos, pero sin aplicarlos
// todavía ("apply false"). Cada módulo decide cuáles aplica.
// Las versiones se leen desde gradle/libs.versions.toml.
// =====================================================================

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}
