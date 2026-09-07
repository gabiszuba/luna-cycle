package com.example.lunacycle.feature.calendar.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.lunacycle.core.designsystem.component.AppCard
import com.example.lunacycle.core.designsystem.component.AppIconBadge
import com.example.lunacycle.core.designsystem.theme.AppSpacing

@Composable
fun InsightCard(title: String, value: String, icon: ImageVector, accent: Color, modifier: Modifier = Modifier) {
    AppCard(modifier = modifier) {
        AppIconBadge(icon = icon, accent = accent)
        Spacer(Modifier.height(AppSpacing.sm))
        Text(title, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(Modifier.height(AppSpacing.xxs))
        Text(value, style = MaterialTheme.typography.titleMedium)
    }
}
