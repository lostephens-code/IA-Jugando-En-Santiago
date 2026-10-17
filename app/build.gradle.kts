// =====================================================================
// app/build.gradle.kts (nivel de MÓDULO)
// ---------------------------------------------------------------------
// Configura cómo se compila la aplicación: versión de Android mínima,
// identificador de la app, y las librerías que utiliza.
// =====================================================================

plugins {
    alias(libs.plugins.android.application) // Es una aplicación Android
    alias(libs.plugins.kotlin.android)      // Escrita en Kotlin
    alias(libs.plugins.kotlin.compose)      // Que usa Jetpack Compose
}

android {
    // Paquete base del código (clase R, BuildConfig, etc.).
    namespace = "com.ejemplos.bars"

    // Versión del SDK con la que se COMPILA el código.
    compileSdk = 36

    defaultConfig {
        applicationId = "com.ejemplos.bars" // Identificador único en el teléfono / Play Store
        minSdk = 26                         // Android 8.0 Oreo: versión MÍNIMA soportada
        targetSdk = 36                      // Versión para la que se probó la app
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true // Activa Jetpack Compose
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)

    // El BOM fija versiones compatibles para todas las librerías de Compose.
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)            // Componentes Material 3 (las barras)
    implementation(libs.androidx.material.icons.core)  // Íconos básicos (menú, buscar, etc.)

    // Solo para depurar: permite ver las @Preview en Android Studio.
    debugImplementation(libs.androidx.ui.tooling)
}
