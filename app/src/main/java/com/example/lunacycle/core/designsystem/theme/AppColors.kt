package com.example.lunacycle.core.designsystem.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

private val Navy = Color(0xFF101126)
private val NavySurface = Color(0xFF1A1C35)
private val NavyContainer = Color(0xFF272A44)
private val Gold = Color(0xFFF1CE76)
private val Cream = Color(0xFFF7F1E3)

val LunaDarkColorScheme = darkColorScheme(
    primary = Gold,
    onPrimary = Navy,
    primaryContainer = Color(0xFF403720),
    onPrimaryContainer = Color(0xFFFFE7AB),
    secondary = Color(0xFFC6B17D),
    onSecondary = Navy,
    secondaryContainer = NavyContainer,
    onSecondaryContainer = Cream,
    tertiary = Color(0xFFD9C89D),
    onTertiary = Navy,
    background = Navy,
    onBackground = Cream,
    surface = NavySurface,
    onSurface = Cream,
    surfaceVariant = NavyContainer,
    onSurfaceVariant = Color(0xFFB4B5C5),
    outline = Color(0xFF565973),
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005)
)

val LunaLightColorScheme = lightColorScheme(
    primary = Color(0xFF795A1B),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFF8E6B8),
    onPrimaryContainer = Color(0xFF302300),
    secondary = Color(0xFF69603F),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFEDE4CC),
    onSecondaryContainer = Navy,
    tertiary = Color(0xFF655536),
    onTertiary = Color.White,
    background = Color(0xFFFAF7F0),
    onBackground = Navy,
    surface = Color(0xFFFFFCF6),
    onSurface = Navy,
    surfaceVariant = Color(0xFFEDE9DF),
    onSurfaceVariant = Color(0xFF5A5C6B),
    outline = Color(0xFF898579),
    error = Color(0xFFBA1A1A),
    onError = Color.White
)

/** Navy and gold accents derived from the application icon. */
object LunaColorRoles {
    val CalendarFollicular = Color(0xFF8D9BB8)
    val CalendarOvulation = Color(0xFFC5A15C)
    val CalendarLuteal = Color(0xFFE4D7B2)
    val OnCalendarPhase = Navy
    val CycleGradientEnd = Color(0xFFAA8140)
    val Shadow = Color.Black
    val Fertility = Color(0xFFC6B17D)
    val DesireInsight = Gold
    val CervicalMucusInsight = Gold
    val MoodInsight = Gold
    val FertilityContainer = Color(0xFFF3E8CB)
    val CycleTrend = NavySurface
    val OnCycleTrend = Cream
    val CycleTrendLabel = Gold
    val CycleTrack = NavyContainer
    val PrimaryOutline = Color(0xFF8C7748)
}