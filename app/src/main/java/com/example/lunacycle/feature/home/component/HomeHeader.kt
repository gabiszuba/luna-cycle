package com.example.lunacycle.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.NotificationsNone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.lunacycle.core.designsystem.theme.AppDimensions

@Composable
fun HomeHeader(
    modifier: Modifier = Modifier,
    userName: String,
    hasUnread: Boolean = false,
    onNotificationsClick: () -> Unit = {},
) {
    Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        androidx.compose.foundation.layout.Column(Modifier.weight(1f)) {
            Text("Bem-vinda de volta,", color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyLarge)
            Text("Olá, $userName", style = MaterialTheme.typography.headlineMedium)
        }
        IconButton(onClick = onNotificationsClick) {
            Box {
                Icon(
                    imageVector = Icons.Rounded.NotificationsNone,
                    contentDescription = "Notificações",
                    modifier = Modifier.size(
                        AppDimensions.Icon.navigation
                    ),
                )

                if (hasUnread) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .size(AppDimensions.notificationDot)
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = CircleShape,
                            ),
                    )
                }
            }
        }
    }
}
