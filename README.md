# MixCassetteTV

Aplicación optimizada para Smart TV / Android TV que combina una interfaz retro inspirada en un casete de audio con un módulo dinámico de letras estilo karaoke y visualización de videos.

## Características Técnicas Implementadas:
- **Reproducción en segundo plano:** Uso de `Foreground Service` y `WakeLock` para evitar interrupciones al minimizar la app o apagar la pantalla.
- **Estabilidad de Letras:** Sistema de reintento automático (*retry*) para garantizar la carga correcta de letras y metadatos desde el primer intento.
- **Gestión de Estado Multimedia:** Control de ciclo de vida optimizado al alternar entre modo audio, video y la playlist principal.
- **Diseño Leanback para Smart TV:** Interfaz horizontal dividida en dos secciones (Casete animado y Panel dinámico) totalmente navegable mediante control remoto.
