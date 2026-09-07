package com.example.lunacycle.feature.home.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import com.example.lunacycle.core.designsystem.component.AppBadge
import com.example.lunacycle.core.designsystem.theme.*
import com.example.lunacycle.feature.calendar.CalendarPhase
import com.example.lunacycle.feature.calendar.CalendarUiState
import com.example.lunacycle.feature.calendar.component.accent
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun CycleOverviewCard(cycleDay: Int, phaseName: String, phaseStatus: String, modifier: Modifier = Modifier) {
    val primary = MaterialTheme.colorScheme.primary
    val background = MaterialTheme.colorScheme.background
    // Match the proportions of the colored days in the demonstration calendar.
    val phaseDays = remember {
        val calendar = CalendarUiState()
        CalendarPhase.entries.map { phase ->
            phase to (1..calendar.daysInMonth).count { calendar.phaseFor(it) == phase }
        }.filter { it.second > 0 }
    }
    val segments = phaseDays.map { (phase, days) -> phase.accent() to days }
    val totalDays = phaseDays.sumOf { it.second }
    val markerDay = (cycleDay - 1).coerceIn(0, totalDays - 1)
    Box(modifier.size(AppDimensions.Cycle.diameter), contentAlignment = Alignment.Center) {
        Canvas(Modifier.fillMaxSize()) {
            val inset = AppDimensions.Cycle.ringInset.toPx()
            val diameter = size.minDimension - inset * 2
            val origin = Offset((size.width - diameter) / 2, (size.height - diameter) / 2)
            val stroke = Stroke(AppDimensions.Cycle.stroke.toPx(), cap = StrokeCap.Butt)
            var startAngle = AppDimensions.Cycle.startAngle
            var elapsedDays = 0
            var markerColor = primary
            segments.forEach { (color, days) ->
                val sweepAngle = 360f * days / totalDays
                drawArc(color, startAngle, sweepAngle, false, origin, Size(diameter, diameter), style = stroke)
                if (markerDay in elapsedDays until elapsedDays + days) markerColor = color
                elapsedDays += days
                startAngle += sweepAngle
            }
            val markerAngle = Math.toRadians((AppDimensions.Cycle.startAngle + 360f * (markerDay + .5f) / totalDays).toDouble())
            val marker = Offset(
                size.width / 2 + diameter / 2 * cos(markerAngle).toFloat(),
                size.height / 2 + diameter / 2 * sin(markerAngle).toFloat()
            )
            drawCircle(LunaColorRoles.Shadow.copy(alpha = AppOpacity.shadow), AppDimensions.Cycle.markerShadowRadius.toPx(), marker + Offset(0f, AppSpacing.hairline.toPx()))
            drawCircle(background, AppDimensions.Cycle.markerBorderRadius.toPx(), marker)
            drawCircle(markerColor, AppDimensions.Cycle.markerRadius.toPx(), marker)
        }
        Column(
            Modifier.size(AppDimensions.Cycle.centerDiameter).shadow(AppElevation.cycle, CircleShape).background(background, CircleShape),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("DIA", color = primary, style = LunaCycleDayLabel)
            Text(cycleDay.toString(), style = MaterialTheme.typography.displayLarge)
            Text(phaseName, color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(AppSpacing.relaxed))
            AppBadge(phaseStatus, icon = Icons.Rounded.AutoAwesome)
        }
    }
}
