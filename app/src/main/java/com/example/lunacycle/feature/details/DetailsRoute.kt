package com.example.lunacycle.feature.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.lunacycle.feature.tracking.TrackingViewModel

@Composable
fun DetailsRoute(
    viewModel: TrackingViewModel,
    onBack: () -> Unit,
    onEdit: () -> Unit,
    onCalendar: () -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    DetailsScreen(state, onBack = onBack, onEdit = onEdit, onCalendar = onCalendar)
}
