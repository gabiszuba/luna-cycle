package com.example.lunacycle.feature.calendar

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CalendarRoute(viewModel: CalendarViewModel, onBack: () -> Unit = {}) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value
    CalendarScreen(state = state, onAction = viewModel::onAction, onBack = onBack)
}
