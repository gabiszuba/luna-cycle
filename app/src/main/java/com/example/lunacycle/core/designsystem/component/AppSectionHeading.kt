package com.example.lunacycle.core.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.lunacycle.core.designsystem.theme.*

@Composable
fun AppSectionHeading(title: String, modifier: Modifier = Modifier, action: String? = null, onAction: (() -> Unit)? = null) {
    Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(title, style = MaterialTheme.typography.titleLarge, modifier = Modifier.weight(1f))
        if (action != null && onAction != null) {
            Row(Modifier.clickable(onClick = onAction), verticalAlignment = Alignment.CenterVertically) {
                Text(action, color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.labelLarge)
                Spacer(Modifier.width(AppSpacing.xxs))
                Icon(Icons.Rounded.ChevronRight, null, Modifier.size(AppDimensions.Icon.small), tint = MaterialTheme.colorScheme.primary)
            }
        }
    }
}
