# PokéAPI Explorer – Android

Aplicación Android desarrollada en Kotlin que consume la **PokéAPI** para mostrar un listado real, paginado y detallado de Pokémon, cumpliendo con los requisitos técnicos del ejercicio E1.

---

## Objetivo del Proyecto

El objetivo principal es implementar un flujo completo de datos desde una API externa, gestionando correctamente el ciclo de vida de la aplicación y los estados de la interfaz de usuario.

### Requisitos Mínimos:
- **Consumo API Real**: Uso de `GET https://pokeapi.co/api/v2/pokemon?limit=&offset=`.
- **Gestión de Estados**: Implementación de estados **Loading**, **Error (con botón de reintento)** y **Vacío**.
- **Paginación**: Navegación funcional con soporte para explorar múltiples páginas (mínimo 3 requerido).
- **Listado**: Cada ítem muestra de forma visible su **imagen, nombre e ID/índice**.
- **Detalle**: Pantalla completa con **nombre, ID, sprite oficial, tipos, altura y peso**.
- **Robustez**: Control de errores en la navegación y en el detalle para evitar cierres inesperados (no crashes).

El objetivo era construir una experiencia fluida que permitiera:
- **Consumo eficiente**: Carga de datos reales mediante paginación dinámica.
- **Robustez**: Navegación sin interrupciones incluso en condiciones críticas (como el cambio a Modo Avión).
- **Consistencia**: Sincronización de datos e imágenes entre las vistas de lista y detalle.

---

## Funcionalidades Clave

### Exploración Pagina a Pagina
- **Carga inteligente**: Listado de 20 Pokémon por página con navegación fluida (Siguiente/Anterior).
- **Identificación rápida**: Cada tarjeta muestra el ID, nombre e imagen en alta resolución.
- **Paginación real**: Uso de parámetros `offset` y `limit` para optimizar el tráfico de datos.

### Detalle Profundo
- **Ficha técnica**: Información detallada que incluye altura, peso y tipos elementales.
- **Arte oficial**: Uso de imágenes de alta calidad (`official-artwork`).

### Resiliencia y Estados de UI
- **Manejo de Errores**: Pantallas de error con botones de reintento que no bloquean la experiencia del usuario.

---

## Stack Tecnológico
- **Lenguaje**: Kotlin + Coroutines para asincronía.
- **UI**: Jetpack Compose (Declarativa y moderna).
- **Arquitectura**: Clean Architecture (Capas de Data, Domain y UI).
- **DI**: Hilt (Dependency Injection) para un código desacoplado y testeable.
- **Networking**: Retrofit + Moshi para el consumo de la API REST.
- **Imágenes**: Coil con configuración avanzada de caché y crossfade.

---

## Demo de Funcionamiento

En el video adjunto (`demo.mp4`) se puede observar:
1. **Flujo Ideal**: Carga rápida y navegación entre varias páginas.
2. **Navegación**: Entrada y salida de detalles sin pérdida de estado.
3. **Prueba de Fuego**: Activación de **Modo Avión** para mostrar la pantalla de error y recuperación instantánea al restaurar la red.

---

## Cómo empezar

1. **Clonar**: `git clone [url-del-repo]`
2. **Abrir**: Importa el proyecto en Android Studio.
3. **Ejecutar**: Dale al Play en el emulador o dispositivo físico.

*Nota: No requiere claves de API, utiliza el endpoint público de PokéAPI.*
