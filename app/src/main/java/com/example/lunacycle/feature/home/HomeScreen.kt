package com.example.lunacycle.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.tooling.preview.Preview
import com.example.lunacycle.core.designsystem.component.*
import com.example.lunacycle.core.designsystem.theme.AppSpacing
import com.example.lunacycle.core.designsystem.theme.AppTheme
import com.example.lunacycle.core.designsystem.theme.LunaColorRoles
import com.example.lunacycle.feature.home.component.*

@Composable
fun HomeScreen(state: HomeUiState, onAction: (HomeAction) -> Unit, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(
            start = AppSpacing.lg, end = AppSpacing.lg,
            top = AppSpacing.xl, bottom = AppSpacing.pageBottom
        )
    ) {
        item {
            HomeHeader(
                modifier = Modifier,
                userName = state.userName,
            )
        }
        item {
            Box(
                Modifier.fillMaxWidth().padding(top = AppSpacing.xxxl, bottom = AppSpacing.hero),
                contentAlignment = Alignment.Center
            ) {
                CycleOverviewCard(state.cycleDay, state.phaseName, state.phaseStatus)
            }
        }
        item {
            AppButton(
                text = "Registrar Sintomas",
                onClick = { onAction(HomeAction.RegisterSymptoms) },
                modifier = Modifier.fillMaxWidth(),
                icon = Icons.Rounded.Add,
                style = AppButtonStyle.Outlined,
                textStyle = MaterialTheme.typography.bodyLarge
            )
        }
        item {
            Spacer(Modifier.height(AppSpacing.section))
            AppSectionHeading("Previsões", action = "Ver Calendário", onAction = { onAction(HomeAction.OpenCalendar) })
            Spacer(Modifier.height(AppSpacing.md))
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(AppSpacing.md)) {
                ForecastCard("PRÓXIMA\nMENSTRUAÇÃO", "Em ${state.daysUntilPeriod} dias", state.nextPeriodDate, MaterialTheme.colorScheme.primary, true, Modifier.weight(1f))
                ForecastCard("FÉRTIL", state.fertileLevel, state.fertileDates, LunaColorRoles.Fertility, false, Modifier.weight(1f))
            }
        }
        item {
            Spacer(Modifier.height(AppSpacing.xl))
            AppSectionHeading("Hoje você pode perceber")
            Spacer(Modifier.height(AppSpacing.md))
        }
        items(state.insights.size, key = { state.insights[it].title }) {
            InsightCard(state.insights[it])
            if (it < state.insights.lastIndex) Spacer(Modifier.height(AppSpacing.sm))
        }
        item {
            Spacer(Modifier.height(AppSpacing.xl))
            AppSectionHeading("Seu dia")
            Spacer(Modifier.height(AppSpacing.md))
        }
        items(state.dailyTips.size, key = { state.dailyTips[it].title }) {
            InsightCard(state.dailyTips[it])
            if (it < state.dailyTips.lastIndex) Spacer(Modifier.height(AppSpacing.sm))
        }
        item {
            Spacer(Modifier.height(AppSpacing.xl))
            HealthTrendCard()
        }
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 1658)
@Composable
private fun HomeScreenPreview() {
    AppTheme(darkTheme = true) { HomeScreen(HomeUiState.fake(), onAction = {}) }
}

@Preview(showBackground = true, widthDp = 412, heightDp = 900)
@Composable
private fun HomeScreenLightPreview() {
    AppTheme(darkTheme = false) { HomeScreen(HomeUiState.fake(), onAction = {}) }
}
