package com.example.lunacycle.feature.home

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeRoute(viewModel: HomeViewModel, onOpenCalendar: () -> Unit = {}) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value
    HomeScreen(state = state, onAction = { action ->
        if (action == HomeAction.OpenCalendar) onOpenCalendar() else viewModel.onAction(action)
    })
}
