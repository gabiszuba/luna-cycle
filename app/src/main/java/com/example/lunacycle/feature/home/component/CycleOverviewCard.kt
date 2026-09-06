package com.example.lunacycle.feature.home.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import com.example.lunacycle.core.designsystem.component.AppBadge
import com.example.lunacycle.core.designsystem.theme.*

@Composable
fun CycleOverviewCard(cycleDay: Int, phaseName: String, phaseStatus: String, modifier: Modifier = Modifier) {
    val primary = MaterialTheme.colorScheme.primary
    val background = MaterialTheme.colorScheme.background
    Box(modifier.size(AppDimensions.Cycle.diameter), contentAlignment = Alignment.Center) {
        Canvas(Modifier.fillMaxSize()) {
            val inset = AppDimensions.Cycle.ringInset.toPx()
            val diameter = size.minDimension - inset * 2
            val origin = Offset((size.width - diameter) / 2, (size.height - diameter) / 2)
            val stroke = Stroke(AppDimensions.Cycle.stroke.toPx(), cap = StrokeCap.Round)
            drawArc(LunaColorRoles.CycleTrack, 0f, 360f, false, origin, Size(diameter, diameter), style = stroke)
            drawArc(Brush.verticalGradient(listOf(primary, LunaColorRoles.CycleGradientEnd)), AppDimensions.Cycle.startAngle, AppDimensions.Cycle.sweepAngle, false, origin, Size(diameter, diameter), style = stroke)
            val marker = Offset(size.width / 2 + diameter / 2, size.height / 2)
            drawCircle(LunaColorRoles.Shadow.copy(alpha = AppOpacity.shadow), AppDimensions.Cycle.markerShadowRadius.toPx(), marker + Offset(0f, AppSpacing.hairline.toPx()))
            drawCircle(background, AppDimensions.Cycle.markerBorderRadius.toPx(), marker)
            drawCircle(primary, AppDimensions.Cycle.markerRadius.toPx(), marker)
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
            AppBadge(phaseStatus, icon = Icons.Outlined.AutoAwesome)
        }
    }
}
