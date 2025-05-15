package com.captures2024.soongan.presentation.feature.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.util.trace
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.captures2024.soongan.core.navigator.screen.main.awards.navigateToAwards
import com.captures2024.soongan.core.navigator.screen.main.feed.navigateToFeed
import com.captures2024.soongan.core.navigator.screen.main.home.navigateToHome
import com.captures2024.soongan.core.navigator.screen.main.profile.navigateToProfile
import com.captures2024.soongan.presentation.feature.main.component.isTopLevelDestinationInHierarchy

@Composable
internal fun rememberMainNavigationState(
    isGuestMode: Boolean,
    navController: NavHostController,
) = remember(
    isGuestMode,
    navController,
) {
    MainNavigationState(
        isGuestMode = isGuestMode,
        navController = navController,
    )
}

@Stable
internal class MainNavigationState(
    val isGuestMode: Boolean,
    val navController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState()
            .value
            ?.destination

    val topLevelDestinations: List<MainTopLevelDestination> = MainTopLevelDestination.entries

    fun navigateToTopLevelDestination(topLevelDestination: MainTopLevelDestination) {
        trace("Navigation: ${topLevelDestination.name}") {
            val topLevelNavOptions = buildTopLevelNavOptions()

            when (topLevelDestination) {
                MainTopLevelDestination.HOME -> {
                    navController.navigateToHome(topLevelNavOptions)
                }
                MainTopLevelDestination.FEED -> {
                    navController.navigateToFeed(topLevelNavOptions)
                }
                MainTopLevelDestination.AWARDS -> {
                    navController.navigateToAwards(topLevelNavOptions)
                }
                MainTopLevelDestination.PROFILE -> {
                    navController.navigateToProfile(topLevelNavOptions)
                }
            }
        }
    }

    /**
     * 바텀 네비게이션 바를 표시할지 안할지 결정하는 함수
     * @return topLevel에 해당한다면 즉, home, feed, awards, profile에 해당하면 false 해당하지 않으면 true
     * **/
    @Composable
    fun isNotViewBottomBar(): Boolean {
        for (topLevelDestination in topLevelDestinations)
            if (currentDestination.isTopLevelDestinationInHierarchy(topLevelDestination))
                return false

        return true
    }

    fun buildTopLevelNavOptions(): NavOptions = navOptions {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
