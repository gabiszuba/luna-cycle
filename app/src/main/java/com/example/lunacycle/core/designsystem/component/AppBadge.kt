package com.example.lunacycle.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.lunacycle.core.designsystem.theme.*

@Composable
fun AppBadge(text: String, modifier: Modifier = Modifier, icon: ImageVector? = null, compact: Boolean = false,
             containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
             contentColor: Color = MaterialTheme.colorScheme.primary) {
    Row(modifier.background(containerColor, CircleShape).padding(
        horizontal = if (compact) AppSpacing.badge else AppSpacing.sm,
        vertical = if (compact) AppSpacing.hairline else AppSpacing.xxs
    ), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(AppSpacing.xxs)) {
        if (icon != null) Icon(icon, null, Modifier.size(AppDimensions.Icon.badge), tint = contentColor)
        Text(text, color = contentColor, style = MaterialTheme.typography.labelMedium)
    }
}
