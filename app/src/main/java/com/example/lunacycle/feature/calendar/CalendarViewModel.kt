package com.example.lunacycle.feature.calendar

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CalendarViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CalendarUiState())
    val uiState = _uiState.asStateFlow()

    fun onAction(action: CalendarAction) {
        _uiState.update { state ->
            when (action) {
                CalendarAction.PreviousMonth -> state.moveMonth(-1)
                CalendarAction.NextMonth -> state.moveMonth(1)
                is CalendarAction.SelectDay -> state.copy(selectedDay = action.day)
            }
        }
    }
}
