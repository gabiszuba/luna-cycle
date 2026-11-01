package com.example.lunacycle.feature.calendar.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.lunacycle.core.designsystem.theme.AppDimensions
import com.example.lunacycle.core.designsystem.theme.AppSpacing

@Composable
fun InsightCard(title: String, value: String, icon: ImageVector, accent: Color, modifier: Modifier = Modifier) {
    Column(modifier.padding(vertical = AppSpacing.sm), verticalArrangement = Arrangement.spacedBy(AppSpacing.xs)) {
        Row(horizontalArrangement = Arrangement.spacedBy(AppSpacing.xs)) {
            Icon(icon, null, Modifier.size(AppDimensions.Icon.small), tint = accent)
            Text(title, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Text(value, style = MaterialTheme.typography.titleMedium)
    }
}