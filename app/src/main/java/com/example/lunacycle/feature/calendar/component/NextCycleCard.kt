package com.example.lunacycle.feature.calendar.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material.icons.rounded.WaterDrop
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.lunacycle.core.designsystem.component.AppCard
import com.example.lunacycle.core.designsystem.component.AppIconBadge
import com.example.lunacycle.core.designsystem.theme.*

@Composable
fun NextCycleCard(modifier: Modifier = Modifier) {
    AppCard(modifier.fillMaxWidth(), containerColor = MaterialTheme.colorScheme.primaryContainer, contentPadding = PaddingValues(AppSpacing.xl)) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(AppSpacing.md)) {
            Column(Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(AppSpacing.xs)) {
                    Icon(Icons.Rounded.Schedule, null, Modifier.size(AppDimensions.Icon.small), tint = MaterialTheme.colorScheme.onPrimaryContainer)
                    Text("PRÓXIMO CICLO", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onPrimaryContainer)
                }
                Spacer(Modifier.height(AppSpacing.sm))
                Text("8 de novembro", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onPrimaryContainer)
                Spacer(Modifier.height(AppSpacing.compact))
                Text("Previsão ilustrativa • ciclo de 28 dias", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onPrimaryContainer)
            }
            AppIconBadge(icon = Icons.Rounded.WaterDrop, accent = MaterialTheme.colorScheme.primary)
        }
    }
}
