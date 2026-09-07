package com.example.lunacycle.feature.calendar.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lunacycle.core.designsystem.theme.*
import com.example.lunacycle.core.designsystem.component.AppCard
import com.example.lunacycle.feature.calendar.*

@Composable
internal fun CalendarPhase.accent(): Color = when (this) {
    CalendarPhase.Menstruation -> MaterialTheme.colorScheme.primary
    CalendarPhase.Follicular -> LunaColorRoles.CalendarFollicular
    CalendarPhase.Ovulation -> LunaColorRoles.CalendarOvulation
    CalendarPhase.Luteal -> LunaColorRoles.CalendarLuteal
}

@Composable

fun MonthCalendar(state: CalendarUiState, onAction: (CalendarAction) -> Unit, modifier: Modifier = Modifier) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(AppSpacing.md)) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Rounded.CalendarMonth, null, tint = MaterialTheme.colorScheme.primary)
            Spacer(Modifier.width(AppSpacing.xs))
            Text("${state.monthName} ${state.year}", style = MaterialTheme.typography.titleMedium, modifier = Modifier.weight(1f))
            IconButton(onClick = { onAction(CalendarAction.PreviousMonth) }) {
                Icon(Icons.AutoMirrored.Rounded.KeyboardArrowLeft, "Mês anterior")
            }
            IconButton(onClick = { onAction(CalendarAction.NextMonth) }) {
                Icon(Icons.AutoMirrored.Rounded.KeyboardArrowRight, "Próximo mês")
            }
        }
        CalendarGlassPanel(selectedDay = state.selectedDay, firstDayOffset = state.firstDayOffset) {
            Row(Modifier.fillMaxWidth().padding(vertical = AppSpacing.sm)) {
                listOf("Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sáb").forEach { label ->
                    Text(label, Modifier.weight(1f), textAlign = TextAlign.Center, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
            Column(Modifier.selectableGroup()) {
                val weekCount = (state.firstDayOffset + state.daysInMonth + 6) / 7
                repeat(weekCount) { week ->
                    Row(Modifier.fillMaxWidth()) {
                        repeat(7) { weekday ->
                            val day = week * 7 + weekday - state.firstDayOffset + 1
                            if (day !in 1..state.daysInMonth) {
                                Spacer(Modifier.weight(1f).height(AppDimensions.button))
                            } else {
                                val phase = state.phaseFor(day)
                                val selected = state.selectedDay == day
                                Box(
                                    Modifier.weight(1f).height(AppDimensions.button)
                                        .clip(CircleShape)
                                        .selectable(selected, role = Role.RadioButton, onClick = { onAction(CalendarAction.SelectDay(day)) })
                                        .semantics { contentDescription = "$day de ${state.monthName} de ${state.year}, ${phase?.label ?: "sem dados de fase"}" },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Box(
                                        Modifier.padding(AppSpacing.hairline).size(AppDimensions.iconBadge)
                                            .then(if (selected) Modifier.border(AppDimensions.border, MaterialTheme.colorScheme.onSurface, CircleShape) else Modifier),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(day.toString(), modifier = Modifier.offset(y = (-3).dp),
                                            style = MaterialTheme.typography.labelLarge,
                                            fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                                            color = MaterialTheme.colorScheme.onSurface)
                                        if (phase != null) {
                                            Box(Modifier.align(Alignment.BottomCenter).padding(bottom = 7.dp)
                                                .width(16.dp).height(3.dp).background(phase.accent(), CircleShape))
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        state.selectedDay?.let { day ->
            Text(
                "$day de ${state.monthName.lowercase()} • ${state.phaseFor(day)?.label ?: "Sem dados de fase"}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun PhaseLegend(modifier: Modifier = Modifier) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(AppSpacing.sm)) {
        CalendarPhase.entries.chunked(2).forEach { phases ->
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(AppSpacing.sm)) {
                phases.forEach { phase ->
                    AppCard(modifier = Modifier.weight(1f), contentPadding = PaddingValues(AppSpacing.sm)) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(AppSpacing.xs)) {
                            Box(Modifier.size(AppDimensions.Icon.badge).background(phase.accent(), CircleShape))
                            Text(phase.label, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}

/** A clear lens with curved edge reflections and light that follows the selected column. */
@Composable
private fun CalendarGlassPanel(
    selectedDay: Int?,
    firstDayOffset: Int,
    content: @Composable ColumnScope.() -> Unit
) {
    val colors = MaterialTheme.colorScheme
    val shape = RoundedCornerShape(28.dp)
    val lightPosition by animateFloatAsState(
        targetValue = selectedDay?.let { ((it + firstDayOffset - 1) % 7 + 0.5f) / 7f } ?: 0.25f,
        animationSpec = tween(450), label = "Calendar glass light"
    )
    Column(
        Modifier.fillMaxWidth()
            .shadow(18.dp, shape, ambientColor = colors.primary.copy(alpha = 0.18f),
                spotColor = Color.Black.copy(alpha = 0.35f))
            .clip(shape)
            .drawWithCache {
                val radius = 28.dp.toPx()
                val corner = CornerRadius(radius)
                val lightX = size.width * lightPosition
                val body = Brush.verticalGradient(listOf(
                    colors.surface.copy(alpha = 0.42f), colors.background.copy(alpha = 0.18f),
                    colors.surface.copy(alpha = 0.3f)
                ))
                // Broad transmitted light is concentrated at the curved lower edge.
                val transmittedLight = Brush.radialGradient(
                    listOf(colors.primary.copy(alpha = 0.3f), colors.primary.copy(alpha = 0.07f), Color.Transparent),
                    center = Offset(lightX, size.height * 1.08f), radius = size.width * 0.85f
                )
                val upperReflection = Brush.radialGradient(
                    listOf(Color.White.copy(alpha = 0.16f), Color.White.copy(alpha = 0.025f), Color.Transparent),
                    center = Offset(size.width * 0.12f, -size.height * 0.3f), radius = size.width * 1.1f
                )
                val rim = Brush.linearGradient(
                    0f to Color.White.copy(alpha = 0.72f),
                    0.22f to Color.White.copy(alpha = 0.22f),
                    0.45f to Color.White.copy(alpha = 0.03f),
                    0.7f to colors.primary.copy(alpha = 0.32f),
                    1f to Color.White.copy(alpha = 0.58f),
                    start = Offset.Zero, end = Offset(size.width, size.height)
                )
                val reflectedArc = Path().apply {
                    moveTo(-radius, size.height * 0.32f)
                    cubicTo(size.width * 0.18f, -size.height * 0.02f,
                        size.width * 0.68f, size.height * 0.3f, size.width + radius, size.height * 0.04f)
                    lineTo(size.width + radius, -radius)
                    lineTo(-radius, -radius)
                    close()
                }
                onDrawBehind {
                    drawRect(body)
                    drawRect(transmittedLight)
                    drawRect(upperReflection)
                    drawPath(reflectedArc, Brush.verticalGradient(listOf(
                        Color.White.copy(alpha = 0.035f), Color.Transparent
                    )))
                    // Several narrow inset rings produce a rounded lens rim rather than a flat outline.
                    for (layer in 1..6) {
                        val inset = layer * 1.1.dp.toPx()
                        drawRoundRect(
                            brush = Brush.verticalGradient(listOf(
                                Color.White.copy(alpha = 0.065f / layer), Color.Transparent,
                                colors.primary.copy(alpha = 0.14f / layer)
                            )),
                            topLeft = Offset(inset, inset),
                            size = Size(size.width - inset * 2, size.height - inset * 2),
                            cornerRadius = CornerRadius((radius - inset).coerceAtLeast(0f)),
                            style = Stroke(1.4.dp.toPx())
                        )
                    }
                    val edgeInset = 0.75.dp.toPx()
                    drawRoundRect(rim, topLeft = Offset(edgeInset, edgeInset),
                        size = Size(size.width - edgeInset * 2, size.height - edgeInset * 2),
                        cornerRadius = corner, style = Stroke(1.5.dp.toPx()))
                    // Bright curved glints at opposite corners give the surface its thickness.
                    drawArc(Color.White.copy(alpha = 0.56f), 185f, 80f, false,
                        topLeft = Offset(edgeInset, edgeInset), size = Size(radius * 2, radius * 2),
                        style = Stroke(1.dp.toPx()))
                    drawArc(colors.onPrimary.copy(alpha = 0.4f), 5f, 75f, false,
                        topLeft = Offset(size.width - radius * 2 - edgeInset, size.height - radius * 2 - edgeInset),
                        size = Size(radius * 2, radius * 2), style = Stroke(1.dp.toPx()))
                }
            }
            .padding(AppSpacing.sm),
        content = content
    )
}
