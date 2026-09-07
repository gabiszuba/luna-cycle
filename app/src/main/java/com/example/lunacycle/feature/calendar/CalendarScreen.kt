package com.example.lunacycle.feature.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.WaterDrop
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lunacycle.core.designsystem.component.AppSectionHeading
import com.example.lunacycle.core.designsystem.component.AppScreenHeader
import com.example.lunacycle.core.designsystem.theme.*
import com.example.lunacycle.feature.calendar.component.*

@Composable
fun CalendarScreen(state: CalendarUiState, onAction: (CalendarAction) -> Unit, onBack: () -> Unit = {}, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(
            start = AppSpacing.lg, end = AppSpacing.lg,
            top = AppSpacing.md, bottom = AppSpacing.pageBottom
        ),
        verticalArrangement = Arrangement.spacedBy(AppSpacing.xl)
    ) {
        item {
            AppScreenHeader(
                title = "Calendário",
                onBack = onBack
            )
        }
        item { MonthCalendar(state, onAction) }
        item {
            Text("LEGENDA DE FASES", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(Modifier.height(AppSpacing.md))
            PhaseLegend()
            Spacer(Modifier.height(AppSpacing.sm))
            Text("Dados demonstrativos", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        item { NextCycleCard() }
        item {
            AppSectionHeading("Insights do seu ciclo")
            Spacer(Modifier.height(AppSpacing.md))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(AppSpacing.md)) {
                InsightCard("Duração média", "28 dias", Icons.Rounded.FavoriteBorder, MaterialTheme.colorScheme.primary, Modifier.weight(1f))
                InsightCard("Fluxo típico", "Moderado", Icons.Rounded.WaterDrop, LunaColorRoles.Fertility, Modifier.weight(1f))
            }
        }
    }

}

@Preview(showBackground = true, widthDp = 390, heightDp = 1150)
@Composable
private fun CalendarScreenPreview() {
    AppTheme(darkTheme = true) { CalendarScreen(CalendarUiState(), {}) }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 1150)
@Composable
private fun CalendarScreenLightPreview() {
    AppTheme(darkTheme = false) { CalendarScreen(CalendarUiState(), {}) }
}
