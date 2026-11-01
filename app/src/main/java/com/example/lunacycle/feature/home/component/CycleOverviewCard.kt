package com.example.lunacycle.feature.home.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import com.example.lunacycle.core.designsystem.component.AppBadge
import com.example.lunacycle.core.designsystem.theme.*
import com.example.lunacycle.feature.calendar.CalendarUiState
import com.example.lunacycle.feature.calendar.component.accent
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun CycleOverviewCard(calendar: CalendarUiState, modifier: Modifier = Modifier, onOpenCalendar: () -> Unit = {}) {
    val colors = MaterialTheme.colorScheme
    val phase = calendar.selectedDay?.let { calendar.phaseFor(it) }
    val dayColors = (1..calendar.daysInMonth).map { day ->
        calendar.phaseFor(day)?.accent() ?: colors.outline.copy(alpha = 0.35f)
    }
    Box(modifier.size(AppDimensions.Cycle.diameter).clickable(
        onClickLabel = "Abrir calendário", onClick = onOpenCalendar
    ), contentAlignment = Alignment.Center) {
        Canvas(Modifier.fillMaxSize()) {
            // Reserve enough space for the marker on every side of the same circular orbit.
            val inset = maxOf(
                AppDimensions.Cycle.ringInset.toPx(),
                AppDimensions.Cycle.markerShadowRadius.toPx()
            )
            val diameter = size.minDimension - inset * 2
            val center = Offset(size.width / 2, size.height / 2)
            val origin = center - Offset(diameter / 2, diameter / 2)
            val stroke = Stroke(AppDimensions.Cycle.stroke.toPx(), cap = StrokeCap.Butt)
            dayColors.forEachIndexed { index, color ->
                drawArc(
                    color, -90f + index * calendar.daySweepAngle,
                    calendar.daySweepAngle, false, origin, Size(diameter, diameter), style = stroke
                )
            }
            calendar.selectedDayAngle?.let { angle ->
                val radians = Math.toRadians(angle.toDouble())
                val marker = center + Offset(
                    diameter / 2 * cos(radians).toFloat(),
                    diameter / 2 * sin(radians).toFloat()
                )
                // Concentric circles keep the marker symmetric around the ring's centerline.
                drawCircle(colors.primary.copy(alpha = 0.14f), AppDimensions.Cycle.markerShadowRadius.toPx(), marker)
                drawCircle(colors.background, AppDimensions.Cycle.markerBorderRadius.toPx(), marker)
                drawCircle(colors.primary, AppDimensions.Cycle.markerRadius.toPx(), marker)
            }
        }
        Column(
            Modifier.size(AppDimensions.Cycle.centerDiameter)
                .shadow(AppElevation.cycle, CircleShape).background(colors.background, CircleShape),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(calendar.monthName.uppercase(), color = colors.primary, style = LunaCycleDayLabel)
            Text(calendar.selectedDay?.toString() ?: "—", style = MaterialTheme.typography.displayLarge)
            Text(calendar.year.toString(), color = colors.onSurfaceVariant, style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.height(AppSpacing.sm))
            AppBadge(
                phase?.label ?: if (calendar.selectedDay == null) "Escolha um dia" else "Sem dados de fase",
                contentColor = colors.primary
            )
        }
    }
}