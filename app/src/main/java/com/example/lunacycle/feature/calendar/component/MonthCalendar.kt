package com.example.lunacycle.feature.calendar.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import com.example.lunacycle.core.designsystem.component.LunaIcons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lunacycle.core.designsystem.theme.*

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
        Row(Modifier.fillMaxWidth().padding(horizontal = AppSpacing.md), verticalAlignment = Alignment.CenterVertically) {
            Icon(LunaIcons.Calendar, null, tint = MaterialTheme.colorScheme.primary)
            Spacer(Modifier.width(AppSpacing.xs))
            Text("${state.monthName} ${state.year}", style = MaterialTheme.typography.titleMedium, modifier = Modifier.weight(1f))
            IconButton(onClick = { onAction(CalendarAction.PreviousMonth) }) {
                Icon(LunaIcons.ChevronLeft, "Mês anterior")
            }
            IconButton(onClick = { onAction(CalendarAction.NextMonth) }) {
                Icon(LunaIcons.ChevronRight, "Próximo mês")
            }
        }
        CalendarPanel {
            Row(Modifier.fillMaxWidth().height(AppDimensions.button), verticalAlignment = Alignment.CenterVertically) {
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
                                        Modifier.size(36.dp)
                                            .then(if (selected) Modifier.border(AppDimensions.border, MaterialTheme.colorScheme.onSurface, CircleShape) else Modifier),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.spacedBy(AppSpacing.xxs, Alignment.CenterVertically)
                                        ) {
                                            Text(day.toString(),
                                                style = MaterialTheme.typography.labelLarge,
                                                fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                                                color = MaterialTheme.colorScheme.onSurface)
                                            Box(
                                                Modifier.width(16.dp).height(3.dp)
                                                    .then(if (phase != null) Modifier.background(phase.accent(), CircleShape) else Modifier)
                                            )
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
                modifier = Modifier.padding(horizontal = AppSpacing.md),
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
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(AppSpacing.md)) {
                phases.forEach { phase ->
                    Row(
                        Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(AppSpacing.xs)
                    ) {
                        Box(Modifier.size(8.dp).background(phase.accent(), CircleShape))
                        Text(phase.label, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}
/** Plain surface with the same inset on every side. */
@Composable
private fun CalendarPanel(content: @Composable ColumnScope.() -> Unit) {
    Column(
        Modifier.fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(20.dp))
            .padding(AppSpacing.md),
        content = content
    )
}