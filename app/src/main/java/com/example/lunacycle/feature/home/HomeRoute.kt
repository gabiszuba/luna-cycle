package com.example.lunacycle.feature.home

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeRoute(viewModel: HomeViewModel) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value
    HomeScreen(state = state, onAction = viewModel::onAction)
}
