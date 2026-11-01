package com.example.lunacycle.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.lunacycle.feature.calendar.CalendarUiState
import java.util.Calendar
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@Composable
fun HomeRoute(viewModel: HomeViewModel, onOpenTracking: () -> Unit, onOpenCalendar: () -> Unit = {}) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value
    val lifecycleOwner = LocalLifecycleOwner.current
    val today by produceState(CalendarUiState.today(), lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            while (isActive) {
                val now = Calendar.getInstance()
                value = CalendarUiState.today(now)
                val nextMidnight = (now.clone() as Calendar).apply {
                    add(Calendar.DAY_OF_MONTH, 1)
                    set(Calendar.HOUR_OF_DAY, 0)
                    set(Calendar.MINUTE, 0)
                    set(Calendar.SECOND, 0)
                    set(Calendar.MILLISECOND, 0)
                }
                delay((nextMidnight.timeInMillis - now.timeInMillis).coerceAtLeast(1_000L))
            }
        }
    }
    HomeScreen(state = state, calendar = today, onAction = { action ->
        when (action) {
            HomeAction.OpenCalendar -> onOpenCalendar()
            HomeAction.RegisterSymptoms, HomeAction.RegisterWellbeing -> onOpenTracking()
        }
    })
}