
package com.example.lunacycle.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.outlined.AddCircleOutline
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lunacycle.core.designsystem.component.AppBottomBar
import com.example.lunacycle.core.designsystem.component.AppNavigationItem
import com.example.lunacycle.core.designsystem.component.AppScaffold
import com.example.lunacycle.feature.home.HomeRoute
import com.example.lunacycle.feature.home.HomeViewModel
import kotlinx.serialization.Serializable

@Serializable
data object Home

@Composable
fun LunaCycleNavigation() {
    val navController = rememberNavController()

    AppScaffold(
        bottomBar = {
            AppBottomBar(
                items = homeNavigationItems,
                selectedKey = "home",
                onItemSelected = {}
            )
        }
    ) {
        NavHost(navController = navController, startDestination = Home) {
            composable<Home> {
                HomeRoute(viewModel = viewModel<HomeViewModel>())
            }
        }
    }
}

private val homeNavigationItems = listOf(
    AppNavigationItem("home", "Início", Icons.Outlined.Home),
    AppNavigationItem("calendar", "Calendário", Icons.Outlined.CalendarMonth, enabled = false),
    AppNavigationItem("details", "Detalhes", Icons.AutoMirrored.Outlined.MenuBook, enabled = false),
    AppNavigationItem("register", "Registrar", Icons.Outlined.AddCircleOutline, enabled = false)
)
