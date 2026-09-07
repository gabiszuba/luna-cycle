
package com.example.lunacycle.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.MenuBook
import androidx.compose.material.icons.rounded.AddCircleOutline
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import com.example.lunacycle.core.designsystem.component.AppBottomBar
import com.example.lunacycle.core.designsystem.component.AppNavigationItem
import com.example.lunacycle.core.designsystem.component.AppScaffold
import com.example.lunacycle.feature.calendar.CalendarRoute
import com.example.lunacycle.feature.calendar.CalendarViewModel
import com.example.lunacycle.feature.home.HomeRoute
import com.example.lunacycle.feature.home.HomeViewModel
import com.example.lunacycle.feature.tracking.TrackingRoute
import com.example.lunacycle.feature.details.DetailsRoute
import com.example.lunacycle.feature.tracking.TrackingViewModel
import kotlinx.serialization.Serializable

@Serializable
data object Home
@Serializable
data object Calendar

@Serializable
data object Tracking

@Serializable
data object Details

@Composable
fun LunaCycleNavigation() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val trackingViewModel: TrackingViewModel = viewModel()
    val selectedKey = when {
        backStackEntry?.destination?.hasRoute<Calendar>() == true -> "calendar"
        backStackEntry?.destination?.hasRoute<Tracking>() == true -> "register"
        backStackEntry?.destination?.hasRoute<Details>() == true -> "details"
        else -> "home"
    }

    fun openTracking() {
        navController.navigate(Tracking) {
            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun openDetails() {
        navController.navigate(Details) {
            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun openCalendar() {
        navController.navigate(Calendar) {
            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
            launchSingleTop = true
            restoreState = true
        }
    }

    AppScaffold(
        bottomBar = {
            AppBottomBar(
                items = homeNavigationItems,
                selectedKey = selectedKey,
                onItemSelected = { key ->
                    when (key) {
                        "calendar" -> openCalendar()
                        "register" -> openTracking()
                        "details" -> openDetails()
                        "home" -> navController.navigate(Home) {
                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = Home,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popEnterTransition = { EnterTransition.None },
            popExitTransition = { ExitTransition.None }
        ) {
            composable<Home> {
                HomeRoute(viewModel = viewModel<HomeViewModel>(), onOpenCalendar = ::openCalendar)
            }
            composable<Calendar> {
                CalendarRoute(viewModel = viewModel<CalendarViewModel>(), onBack = { navController.popBackStack() })
            }
            composable<Tracking> {
                TrackingRoute(trackingViewModel, onBack = { navController.popBackStack() }, onSaved = ::openDetails)
            }
            composable<Details> {
                DetailsRoute(trackingViewModel, onBack = { navController.popBackStack() }, onEdit = ::openTracking, onCalendar = ::openCalendar)
            }
        }
    }
}

private val homeNavigationItems = listOf(
    AppNavigationItem("home", "Início", Icons.Rounded.Home),
    AppNavigationItem("calendar", "Calendário", Icons.Rounded.CalendarMonth),
    AppNavigationItem("details", "Detalhes", Icons.AutoMirrored.Rounded.MenuBook),
    AppNavigationItem("register", "Registrar", Icons.Rounded.AddCircleOutline)
)
