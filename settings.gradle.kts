// =====================================================================
// settings.gradle.kts
// ---------------------------------------------------------------------
// Primer archivo que lee Gradle. Aquí se indica:
//  1. Dónde buscar los plugins (pluginManagement).
//  2. Dónde buscar las librerías (dependencyResolutionManagement).
//  3. Qué módulos forman el proyecto (include).
// =====================================================================

pluginManagement {
    repositories {
        google()            // Plugins de Android (AGP)
        mavenCentral()      // Plugins de Kotlin y otros
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    // Obliga a declarar los repositorios solo aquí (buena práctica).
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

// Nombre del proyecto que verás en Android Studio.
rootProject.name = "Bars"

// Nuestro único módulo: la aplicación.
include(":app")
