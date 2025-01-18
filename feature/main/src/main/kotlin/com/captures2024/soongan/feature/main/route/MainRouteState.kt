package com.captures2024.soongan.feature.main.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.util.trace
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.captures2024.soongan.core.navigator.screen.main.awards.navigateToAwards
import com.captures2024.soongan.core.navigator.screen.main.feed.navigateToFeed
import com.captures2024.soongan.core.navigator.screen.main.home.navigateToHome
import com.captures2024.soongan.core.navigator.screen.main.profile.navigateToProfile
import com.captures2024.soongan.feature.main.navigation.TopLevelDestination

@Composable
internal fun rememberMainRouteState(
    navController: NavHostController = rememberNavController(),
): MainRouteState = remember(navController) {
    MainRouteState(navController = navController)
}


@Stable
internal class MainRouteState(
    val navController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState()
            .value
            ?.destination

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation: ${topLevelDestination.name}") {
            val topLevelNavOptions = navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }

            when (topLevelDestination) {
                TopLevelDestination.HOME -> {
                    navController.navigateToHome(topLevelNavOptions)
                }
                TopLevelDestination.FEED -> {
                    navController.navigateToFeed(topLevelNavOptions)
                }
                TopLevelDestination.AWARDS -> {
                    navController.navigateToAwards(topLevelNavOptions)
                }
                TopLevelDestination.PROFILE -> {
                    navController.navigateToProfile(topLevelNavOptions)
                }
            }
        }
    }
}
