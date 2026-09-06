package com.example.lunacycle.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.lunacycle.core.designsystem.theme.AppDimensions

@Composable
fun AppIconBadge(icon: ImageVector, accent: Color, modifier: Modifier = Modifier,
                 containerColor: Color = MaterialTheme.colorScheme.primaryContainer, circular: Boolean = false) {
    Box(modifier.size(AppDimensions.iconBadge).background(containerColor, if (circular) CircleShape else MaterialTheme.shapes.extraSmall), contentAlignment = Alignment.Center) {
        Icon(icon, null, Modifier.size(if (circular) AppDimensions.Icon.standard else AppDimensions.Icon.insight), tint = accent)
    }
}
