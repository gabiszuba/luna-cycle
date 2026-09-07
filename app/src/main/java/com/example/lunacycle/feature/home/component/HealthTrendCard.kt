package com.example.lunacycle.feature.home.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.TrendingUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.lunacycle.core.designsystem.component.AppBadge
import com.example.lunacycle.core.designsystem.component.AppCard
import com.example.lunacycle.core.designsystem.theme.*

@Composable
fun HealthTrendCard(modifier: Modifier = Modifier) {
    AppCard(modifier.fillMaxWidth().heightIn(min = AppDimensions.trendCard), containerColor = LunaColorRoles.CycleTrend, contentPadding = PaddingValues(AppSpacing.xl)) {
        Box {
            Icon(Icons.AutoMirrored.Rounded.TrendingUp, null, Modifier.align(Alignment.TopEnd).size(AppDimensions.trendIllustration), tint = MaterialTheme.colorScheme.primary.copy(alpha = AppOpacity.decorative))
            Column {
                AppBadge("Ciclo Atual", compact = true,
                    containerColor = MaterialTheme.colorScheme.primary.copy(alpha = AppOpacity.decorative),
                    contentColor = LunaColorRoles.CycleTrendLabel)
                Spacer(Modifier.height(AppSpacing.badge))
                Text("Tendência de Saúde", style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(AppSpacing.compact))
                Text("Seu ciclo está 95% mais regular do que no mês passado. Continue registrando!", color = LunaColorRoles.OnCycleTrend, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
