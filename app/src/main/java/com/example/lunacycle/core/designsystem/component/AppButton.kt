package com.example.lunacycle.core.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
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
    enabled: Boolean = true,
    borderColor: androidx.compose.ui.graphics.Color = LunaColorRoles.PrimaryOutline,
    textStyle: TextStyle = if (style == AppButtonStyle.Filled) MaterialTheme.typography.bodyLarge else MaterialTheme.typography.titleLarge
) {
    val content: @Composable RowScope.() -> Unit = {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(AppSpacing.xs, Alignment.CenterHorizontally)
        ) {
        if (icon != null) {
            Icon(icon, null, Modifier.size(AppDimensions.Icon.standard))

        }
        Text(text, style = textStyle, textAlign = TextAlign.Center)
        }
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
            border = BorderStroke(AppDimensions.border, borderColor),
            content = content
        )
    }
}
