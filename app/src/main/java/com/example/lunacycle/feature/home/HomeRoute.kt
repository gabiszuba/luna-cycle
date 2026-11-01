package com.example.lunacycle.feature.home

import androidx.compose.runtime.Composable
import com.example.lunacycle.feature.calendar.CalendarViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeRoute(viewModel: HomeViewModel, calendarViewModel: CalendarViewModel, onOpenCalendar: () -> Unit = {}) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value
    val calendar = calendarViewModel.uiState.collectAsStateWithLifecycle().value
    HomeScreen(state = state, calendar = calendar, onAction = { action ->
        if (action == HomeAction.OpenCalendar) onOpenCalendar() else viewModel.onAction(action)
    })
}
