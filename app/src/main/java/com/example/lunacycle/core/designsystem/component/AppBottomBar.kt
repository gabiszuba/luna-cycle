package com.example.lunacycle.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.indication
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import com.example.lunacycle.core.designsystem.theme.*

@Immutable
data class AppNavigationItem(val key: String, val label: String, val icon: ImageVector, val enabled: Boolean = true)

/** Presentation only: destinations and selection are owned by the app shell. */
@Composable
fun AppBottomBar(items: List<AppNavigationItem>, selectedKey: String, onItemSelected: (String) -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier.fillMaxWidth().shadow(AppElevation.navigation)
            .background(MaterialTheme.colorScheme.background).navigationBarsPadding()
            .selectableGroup()
    ) {
        items.forEach { item ->
            val selected = item.key == selectedKey
            val color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
            val interactionSource = remember { MutableInteractionSource() }
            Box(
                Modifier.weight(1f).height(AppDimensions.Navigation.barHeight)
                    .selectable(selected, interactionSource = interactionSource, indication = null,
                        enabled = item.enabled, role = Role.Tab, onClick = { onItemSelected(item.key) }),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    Modifier.size(AppDimensions.Navigation.barHeight).clip(CircleShape)
                        .indication(interactionSource, ripple())
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(AppSpacing.hairline, Alignment.CenterVertically)
                ) {
                    Icon(item.icon, null, Modifier.size(AppDimensions.Icon.navigation), tint = color)
                    Text(item.label, color = color, style = MaterialTheme.typography.labelSmall, textAlign = TextAlign.Center)
                }
            }
        }
    }
}
