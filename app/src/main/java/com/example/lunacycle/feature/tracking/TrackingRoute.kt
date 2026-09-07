package com.example.lunacycle.feature.tracking

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun TrackingRoute(viewModel: TrackingViewModel, onBack: () -> Unit, onSaved: () -> Unit) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    TrackingScreen(state, onAction = { action ->
        viewModel.onAction(action)
        if (action == TrackingAction.Save && viewModel.uiState.value.isSaved) onSaved()
    }, onBack = onBack)
}
