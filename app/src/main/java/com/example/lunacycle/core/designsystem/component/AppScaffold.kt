package com.example.lunacycle.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/** Owns persistent chrome and system insets outside individual destinations. */
@Composable
fun AppScaffold(modifier: Modifier = Modifier, bottomBar: @Composable () -> Unit, content: @Composable () -> Unit) {
    Column(modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).statusBarsPadding()) {
        Box(Modifier.weight(1f)) { content() }
        bottomBar()
    }
}
