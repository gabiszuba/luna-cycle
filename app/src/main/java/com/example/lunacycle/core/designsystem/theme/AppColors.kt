package com.example.lunacycle.core.designsystem.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

private val Ink = Color(0xFF1E1F23)
private val Surface = Color(0xFF2A2B2F)
private val SurfaceVariant = Color(0xFF343539)
private val Pink = Color(0xFFEB4899)
private val PinkContainer = Color(0xFF382735)
private val SoftWhite = Color(0xFFF5F2F5)
private val Muted = Color(0xFFA2A3A7)
private val Outline = Color(0xFF38393D)

val LunaDarkColorScheme = darkColorScheme(
    primary = Pink,
    onPrimary = Color.White,
    primaryContainer = PinkContainer,
    onPrimaryContainer = Color(0xFFFFD9E9),
    secondary = Color(0xFF72E9B2),
    onSecondary = Color(0xFF003823),
    background = Ink,
    onBackground = SoftWhite,
    surface = Surface,
    onSurface = SoftWhite,
    surfaceVariant = SurfaceVariant,
    onSurfaceVariant = Muted,
    outline = Outline,
    error = Color(0xFFFFB4AB),
    onError = Color(0xFFA82026)
)

val LunaLightColorScheme = lightColorScheme(
    primary = Color(0xFFB61668),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFFD9E9),
    onPrimaryContainer = Color(0xFF3E0020),
    secondary = Color(0xFF006C46),
    onSecondary = Color.White,
    background = Color(0xFFFFF8FA),
    onBackground = Color(0xFF211A1D),
    surface = Color(0xFFFFF8FA),
    onSurface = Color(0xFF211A1D),
    surfaceVariant = Color(0xFFF3DDE5),
    onSurfaceVariant = Color(0xFF514348),
    outline = Color(0xFF837378),
    error = Color(0xFFBA1A1A),
    onError = Color.White
)

/** Semantic product accents that are not represented by Material's ColorScheme. */
object LunaColorRoles {
    val CycleGradientEnd = Color(0xFFBB347D)
    val Shadow = Color.Black
    val Fertility = Color(0xFF50D69B)
    val DesireInsight = Color(0xFFFF7A14)
    val CervicalMucusInsight = Color(0xFF3B82F6)
    val MoodInsight = Color(0xFFB160FF)
    val FertilityContainer = Color(0xFFDEFFE8)
    val CycleTrend = Color(0xFF400022)
    val OnCycleTrend = Color(0xFFD2AFBF)
    val CycleTrendLabel = Color(0xFFEB4899)
    val CycleTrack = Color(0xFF25262A)
    val PinkOutline = Color(0xFF432B3B)
}
