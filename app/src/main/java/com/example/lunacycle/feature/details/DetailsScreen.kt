package com.example.lunacycle.feature.details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.lunacycle.core.designsystem.theme.*
import com.example.lunacycle.core.designsystem.component.AppScreenHeader

import com.example.lunacycle.feature.tracking.DailyEntry
import com.example.lunacycle.feature.tracking.TrackingUiState
import com.example.lunacycle.feature.tracking.TrackingHeader
import com.example.lunacycle.feature.tracking.SectionLabel

@Composable
fun DetailsScreen(state: TrackingUiState, onBack: () -> Unit, onEdit: () -> Unit, onCalendar: () -> Unit, modifier: Modifier = Modifier) {
    val entry = state.savedEntry
    LazyColumn(modifier.fillMaxSize(), contentPadding = PaddingValues(horizontal = AppSpacing.lg, vertical = AppSpacing.md),
        verticalArrangement = Arrangement.spacedBy(AppSpacing.hero)) {
        item { AppScreenHeader("Detalhes", onBack) }
        item {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text(if (state.isToday) "Hoje" else "Seu registro", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Spacer(Modifier.height(AppSpacing.xxs))
                    Text(state.dateLabel, style = MaterialTheme.typography.titleMedium)
                }
                OutlinedButton(onClick = onCalendar, border = BorderStroke(1.dp, MaterialTheme.colorScheme.primaryContainer), contentPadding = PaddingValues(horizontal = 12.dp)) {
                    Icon(Icons.Rounded.CalendarMonth, null, Modifier.size(16.dp))
                    Spacer(Modifier.width(AppSpacing.xs))
                    Text("Calendário", style = MaterialTheme.typography.labelMedium)
                }
            }
        }
        item {
            Text(if (entry == null) "Seu corpo, seu ritmo" else "Um olhar sobre seu dia", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(AppSpacing.xs))
            Text(if (entry == null) "Registre como você está se sentindo." else "Cada registro conta a sua história.", color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.bodyMedium)
        }
        item {
            SectionLabel("SEUS REGISTROS")
            Spacer(Modifier.height(AppSpacing.lg))
            DetailRow("Humor", entry?.mood)
            DetailRow("Energia", entry?.energy)
            DetailRow("Fluxo", entry?.flow)
            if (!entry?.symptoms.isNullOrEmpty()) {
                Spacer(Modifier.height(AppSpacing.sm))
                FlowRow(horizontalArrangement = Arrangement.spacedBy(AppSpacing.xs), verticalArrangement = Arrangement.spacedBy(AppSpacing.xs)) {
                    entry.symptoms.forEach { symptom ->
                        Surface(color = MaterialTheme.colorScheme.primaryContainer, shape = RoundedCornerShape(50)) {
                            Text(symptom, Modifier.padding(horizontal = 12.dp, vertical = 6.dp), style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onPrimaryContainer)
                        }
                    }
                }
            }
        }
        item {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.weight(1f)) { SectionLabel("SEU DIA") }
                TextButton(onClick = onEdit) {
                    Icon(Icons.Rounded.Edit, null, Modifier.size(14.dp))
                    Spacer(Modifier.width(AppSpacing.xxs))
                    Text("Editar")
                }
            }
            Surface(modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(16.dp), border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)) {
                Text(entry?.notes?.takeIf { it.isNotBlank() } ?: "Um espaço para suas percepções, sentimentos e pequenos detalhes do dia.",
                    modifier = Modifier.padding(AppSpacing.md), style = MaterialTheme.typography.bodyLarge,
                    fontStyle = FontStyle.Italic, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        item {
            Button(onClick = onEdit, modifier = Modifier.fillMaxWidth().heightIn(min = 52.dp), shape = RoundedCornerShape(50)) {
                Text(if (entry == null) "Criar registro" else "Editar registros")
            }
        }
    }
}

@Composable
private fun DetailRow(label: String, value: String?) {
    Row(Modifier.fillMaxWidth().padding(vertical = AppSpacing.sm)) {
        Text(label, Modifier.weight(1f), style = MaterialTheme.typography.bodyMedium)
        Text(value ?: "Não registrado", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
private fun DetailsScreenPreview() {
    AppTheme(darkTheme = true) { Surface { DetailsScreen(TrackingUiState(savedEntry = DailyEntry("Leve", setOf("Cólicas", "Inchaço", "Dor de cabeça"), "Tranquila", "Média", "Hoje me senti bem disposta pela manhã, mas notei uma leve dor de cabeça ao final do dia. Mantendo o foco na hidratação.")), {}, {}, {}) } }
}
