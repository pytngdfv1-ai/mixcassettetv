# Reglas de ofuscación y optimización de ProGuard para MixCassetteTV
-keep class com.mixcassettetv.** { * }

# Mantener clases de Leanback si se extienden componentes de UI para TV
-keep public class * extends androidx.leanback.widget.Presenter
