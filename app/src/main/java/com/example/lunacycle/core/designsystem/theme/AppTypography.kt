package com.example.lunacycle.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.lunacycle.R

private val Poppins = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_semibold, FontWeight.SemiBold),
    Font(R.font.poppins_bold, FontWeight.Bold)
)

private val Inter = FontFamily(
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_medium, FontWeight.Medium),
    Font(R.font.inter_semibold, FontWeight.SemiBold),
    Font(R.font.inter_bold, FontWeight.Bold)
)

private val BaseTypography = Typography(
    displayLarge = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Bold, fontSize = 60.sp, lineHeight = 66.sp, letterSpacing = (-2).sp),
    headlineLarge = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Bold, fontSize = 32.sp, lineHeight = 38.sp),
    headlineMedium = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Bold, fontSize = 28.sp, lineHeight = 34.sp),
    titleLarge = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Bold, fontSize = 20.sp, lineHeight = 26.sp),
    titleMedium = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.SemiBold, fontSize = 16.sp, lineHeight = 21.sp),
    bodyLarge = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Normal, fontSize = 16.sp, lineHeight = 24.sp),
    bodyMedium = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Normal, fontSize = 12.sp, lineHeight = 17.sp),
    labelLarge = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Medium, fontSize = 14.sp, lineHeight = 20.sp),
    labelMedium = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Medium, fontSize = 12.sp, lineHeight = 17.sp),
    labelSmall = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Medium, fontSize = 10.sp, lineHeight = 14.sp)
)

val LunaTypography = BaseTypography.copy(
    displayLarge = BaseTypography.displayLarge.copy(fontFamily = Poppins),
    displayMedium = BaseTypography.displayMedium.copy(fontFamily = Poppins),
    displaySmall = BaseTypography.displaySmall.copy(fontFamily = Poppins),
    headlineLarge = BaseTypography.headlineLarge.copy(fontFamily = Poppins),
    headlineMedium = BaseTypography.headlineMedium.copy(fontFamily = Poppins),
    headlineSmall = BaseTypography.headlineSmall.copy(fontFamily = Poppins),
    titleLarge = BaseTypography.titleLarge.copy(fontFamily = Poppins),
    titleMedium = BaseTypography.titleMedium.copy(fontFamily = Poppins),
    titleSmall = BaseTypography.titleSmall.copy(fontFamily = Poppins),
    bodyLarge = BaseTypography.bodyLarge.copy(fontFamily = Inter),
    bodyMedium = BaseTypography.bodyMedium.copy(fontFamily = Inter),
    bodySmall = BaseTypography.bodySmall.copy(fontFamily = Inter),
    labelLarge = BaseTypography.labelLarge.copy(fontFamily = Inter),
    labelMedium = BaseTypography.labelMedium.copy(fontFamily = Inter),
    labelSmall = BaseTypography.labelSmall.copy(fontFamily = Inter)
)

val LunaCycleDayLabel = LunaTypography.labelLarge.copy(letterSpacing = 1.sp)
