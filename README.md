# Barras Android: ejemplos de TopAppBar con Jetpack Compose

App de ejemplo con las 4 barras superiores de **Material 3**:

| Barra | Pantalla de ejemplo | Comportamiento al desplazar |
|---|---|---|
| `TopAppBar` | Bandeja de entrada | `pinnedScrollBehavior`: queda fija |
| `CenterAlignedTopAppBar` | Mi música | `enterAlwaysScrollBehavior`: se esconde y reaparece |
| `MediumTopAppBar` | Recetas favoritas | `exitUntilCollapsedScrollBehavior`: se encoge |
| `LargeTopAppBar` | Mis viajes | `exitUntilCollapsedScrollBehavior`: se encoge |

Cada botón de acción muestra un mensaje (Toast) con su nombre.

## Requisitos
- Android Studio (trae su propio JDK)
- Android SDK 36 para compilar
- Dispositivo o emulador con **Android 8.0 Oreo (API 26)** o superior

## Gradle sin Internet
Gradle viene **dentro del proyecto**:
- `gradle/wrapper/gradle-8.14.3-bin.zip`: la distribución original.
- `gradle/dists/`: Gradle ya instalado (descomprimido) y listo para usar.

El archivo `gradle/wrapper/gradle-wrapper.properties` apunta al zip con
una ruta relativa y usa `distributionBase=PROJECT`, así que Gradle nunca
se descarga de Internet ni se instala fuera de esta carpeta.

Al abrir el proyecto, Android Studio muestra "Importing Gradle Project"
(sincronización). Es normal: así lee la configuración del proyecto.

### Recomendaciones en Android Studio
1. **Sincronizar el proyecto con los archivos Gradle.**
   Es muy común que tras crear, mover archivos o abrir el proyecto por
   primera vez, Android Studio pierda la sincronización interna.
   - Ve al menú superior y selecciona **File > Sync Project with Gradle Files**.
   - También puedes presionar el ícono del elefante con una flecha azul
     hacia abajo que se encuentra en la barra de herramientas superior derecha.
   - Revisa la pestaña **Build** en la parte inferior para asegurarte de que
     no se presenten errores de dependencias o de sintaxis en tus archivos
     `.gradle`.
2. Si quieres que la sincronización nunca intente conectarse a Internet,
   abre el panel **Gradle** (a la derecha) y activa el botón
   **Toggle Offline Mode**.

> Las librerías (Compose, Material 3...) sí se descargan de Google/Maven
> la primera vez, y luego quedan en caché.

## Estructura del código
```
app/src/main/java/com/ejemplos/bars/
├── MainActivity.kt          ← Punto de entrada y navegación entre pantallas
├── Pantalla.kt              ← Lista (enum) de todas las pantallas
└── ui/
    ├── theme/               ← Colores y tema Material 3
    ├── components/          ← Piezas reutilizables (lista, mensajes)
    └── screens/             ← Menú + una pantalla por cada barra
```

## Cómo ejecutarla
Abre la carpeta `bars` en Android Studio y presiona ▶ **Run**, o desde la terminal:
```
gradlew.bat installDebug
```
