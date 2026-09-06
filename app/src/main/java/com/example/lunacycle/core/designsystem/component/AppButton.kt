package com.example.lunacycle.core.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import com.example.lunacycle.core.designsystem.theme.*

enum class AppButtonStyle { Filled, Outlined }

@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    style: AppButtonStyle = AppButtonStyle.Filled,
    enabled: Boolean = true
) {
    val content: @Composable RowScope.() -> Unit = {
        if (icon != null) {
            Icon(icon, null, Modifier.size(AppDimensions.Icon.standard))
            Spacer(Modifier.width(AppSpacing.xs))
        }
        Text(text, style = if (style == AppButtonStyle.Filled) MaterialTheme.typography.bodyLarge else MaterialTheme.typography.titleLarge, textAlign = TextAlign.Center)
    }
    when (style) {
        AppButtonStyle.Filled -> Button(
            onClick, modifier.heightIn(min = AppDimensions.button), enabled = enabled,
            shape = MaterialTheme.shapes.small,
            elevation = ButtonDefaults.buttonElevation(defaultElevation = AppElevation.button),
            content = content
        )
        AppButtonStyle.Outlined -> OutlinedButton(
            onClick, modifier.heightIn(min = AppDimensions.outlinedButton), enabled = enabled,
            shape = MaterialTheme.shapes.medium,
            border = BorderStroke(AppDimensions.border, LunaColorRoles.PinkOutline),
            content = content
        )
    }
}
