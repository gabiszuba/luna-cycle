package com.example.lunacycle.feature.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bolt
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.lunacycle.core.designsystem.theme.AppDimensions
import com.example.lunacycle.core.designsystem.component.AppIconBadge
import com.example.lunacycle.core.designsystem.component.AppCard
import com.example.lunacycle.core.designsystem.theme.AppSpacing
import com.example.lunacycle.core.designsystem.theme.LunaColorRoles

@Composable
fun ForecastCard(
    title: String,
    value: String,
    detail: String,
    accent: Color,
    isPeriod: Boolean,
    modifier: Modifier = Modifier
) {
    AppCard(modifier = modifier.height(AppDimensions.forecastCard), contentPadding = PaddingValues(AppSpacing.md)) {
        Column(verticalArrangement = Arrangement.spacedBy(AppSpacing.xxs)) {
            AppIconBadge(
                icon = if (isPeriod) Icons.Outlined.WaterDrop else Icons.Outlined.Bolt,
                accent = accent,
                containerColor = if (isPeriod) MaterialTheme.colorScheme.primaryContainer else LunaColorRoles.FertilityContainer,
                circular = true
            )
            Spacer(Modifier.height(AppSpacing.xxs))
            Text(title, color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.labelMedium)
            Text(value, style = MaterialTheme.typography.titleLarge)
            Text(detail, color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
