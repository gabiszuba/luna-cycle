package com.example.lunacycle.feature.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.LocalFireDepartment
import androidx.compose.material.icons.rounded.Air
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.lunacycle.core.designsystem.theme.AppDimensions
import com.example.lunacycle.core.designsystem.component.AppIconBadge
import com.example.lunacycle.core.designsystem.component.AppCard
import com.example.lunacycle.core.designsystem.theme.AppSpacing
import com.example.lunacycle.core.designsystem.theme.LunaColorRoles
import com.example.lunacycle.feature.home.HomeInsight
import com.example.lunacycle.feature.home.InsightKind

@Composable
fun InsightCard(insight: HomeInsight, modifier: Modifier = Modifier) {
    val (icon, accent) = when (insight.kind) {
        InsightKind.Desire -> Icons.Rounded.LocalFireDepartment to LunaColorRoles.DesireInsight
        InsightKind.CervicalMucus -> Icons.Rounded.Air to LunaColorRoles.CervicalMucusInsight
        InsightKind.Tip -> Icons.Rounded.AutoAwesome to MaterialTheme.colorScheme.primary
        InsightKind.Mood -> Icons.Rounded.Info to LunaColorRoles.MoodInsight
    }
    AppCard(
        modifier = modifier.fillMaxWidth().heightIn(min = AppDimensions.insightCard),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(AppSpacing.md)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(AppSpacing.md)) {
            AppIconBadge(
                icon = icon, accent = accent
            )
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(AppSpacing.xxs)) {
                Text(insight.title, style = MaterialTheme.typography.titleMedium)
                Text(
                    insight.description,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyMedium,
                    minLines = 2
                )
            }
            Icon(Icons.Rounded.ChevronRight, contentDescription = null, tint = MaterialTheme.colorScheme.outline, modifier = Modifier.size(AppDimensions.Icon.chevron))
        }
    }
}
