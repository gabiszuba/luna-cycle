package com.example.lunacycle.feature.home.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.lunacycle.core.designsystem.component.LunaIcons
import com.example.lunacycle.core.designsystem.theme.AppDimensions
import com.example.lunacycle.core.designsystem.theme.AppSpacing
import com.example.lunacycle.feature.home.HomeInsight
import com.example.lunacycle.feature.home.InsightKind

@Composable
fun InsightCard(insight: HomeInsight, modifier: Modifier = Modifier) {
    val icon = when (insight.kind) {
        InsightKind.Desire -> LunaIcons.Heart
        InsightKind.CervicalMucus -> LunaIcons.Wind
        InsightKind.Tip -> LunaIcons.Sparkles
        InsightKind.Mood -> LunaIcons.Smile
    }
    Row(
        modifier.fillMaxWidth().padding(vertical = AppSpacing.sm),
        horizontalArrangement = Arrangement.spacedBy(AppSpacing.md)
    ) {
        Icon(
            icon, null,
            Modifier.padding(top = AppSpacing.xxs).size(AppDimensions.Icon.insight),
            tint = MaterialTheme.colorScheme.primary
        )
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(AppSpacing.xxs)) {
            Text(insight.title, style = MaterialTheme.typography.titleMedium)
            Text(
                insight.description,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}