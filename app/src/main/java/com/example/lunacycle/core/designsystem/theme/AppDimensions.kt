package com.example.lunacycle.core.designsystem.theme

import androidx.compose.ui.unit.dp

/** Shared component dimensions; screen composition uses AppSpacing. */
object AppDimensions {
    val border = 1.dp
    val notificationDot = 4.dp
    val iconBadge = 40.dp
    val button = 48.dp
    val outlinedButton = 56.dp
    val forecastCard = 168.dp
    val insightCard = 88.dp
    val trendCard = 152.dp
    val trendIllustration = 92.dp

    object Icon {
        val badge = 14.dp
        val small = 18.dp
        val chevron = 20.dp
        val insight = 22.dp
        val standard = 24.dp
        val navigation = 26.dp
    }

    object Navigation {
        val itemMinHeight = 44.dp
    }

    object Cycle {
        val diameter = 268.dp
        val centerDiameter = 194.dp
        val ringInset = 10.dp
        val stroke = 12.dp
        val markerShadowRadius = 14.dp
        val markerBorderRadius = 12.dp
        val markerRadius = 8.dp
        const val startAngle = -90f
        const val sweepAngle = 180f
    }
}

object AppElevation {
    val card = 3.dp
    val button = 6.dp
    val navigation = 8.dp
    val cycle = 18.dp
}

object AppOpacity {
    const val decorative = .18f
    const val shadow = .25f
}
