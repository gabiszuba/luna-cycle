package com.example.lunacycle.core.designsystem.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

private val Ink = Color(0xFF1C1A23)
private val Surface = Color(0xFF292532)
private val SurfaceVariant = Color(0xFF36303F)
private val Violet = Color(0xFF9956F6)
private val VioletContainer = Color(0xFF38224F)
private val SoftWhite = Color(0xFFF4F2FA)
private val Muted = Color(0xFFA2A3A7)
private val Outline = Color(0xFF463951)

val LunaDarkColorScheme = darkColorScheme(
    primary = Violet,
    onPrimary = Color.White,
    primaryContainer = VioletContainer,
    onPrimaryContainer = Color(0xFFE5DEFF),
    secondary = Color(0xFF2DE5AB),
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
    primary = Violet,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFE5DEFF),
    onPrimaryContainer = Color(0xFF21104B),
    secondary = Color(0xFF006C46),
    onSecondary = Color.White,
    background = Color(0xFFFAF8FF),
    onBackground = Color(0xFF1E1B29),
    surface = Color(0xFFFAF8FF),
    onSurface = Color(0xFF1E1B29),
    surfaceVariant = Color(0xFFE7E1F2),
    onSurfaceVariant = Color(0xFF494453),
    outline = Color(0xFF7A7487),
    error = Color(0xFFBA1A1A),
    onError = Color.White
)

/** Semantic product accents that are not represented by Material's ColorScheme. */
object LunaColorRoles {
    val CalendarFollicular = Color(0xFF2DE5AB)
    val CalendarOvulation = Color(0xFFFF66C4)
    val CalendarLuteal = Color(0xFFFFCE45)
    val OnCalendarPhase = Color(0xFF292438)
    val CycleGradientEnd = Color(0xFF6526CF)
    val Shadow = Color.Black
    val Fertility = Color(0xFF2DE5AB)
    val DesireInsight = Color(0xFFFF7A14)
    val CervicalMucusInsight = Color(0xFF3B82F6)
    val MoodInsight = Color(0xFFB160FF)
    val FertilityContainer = Color(0xFFDEFFE8)
    val CycleTrend = Color(0xFF302044)
    val OnCycleTrend = Color(0xFFC8BFDF)
    val CycleTrendLabel = Color(0xFFC69BFF)
    val CycleTrack = Color(0xFF25262A)
    val PrimaryOutline = Color(0xFF634187)
}
