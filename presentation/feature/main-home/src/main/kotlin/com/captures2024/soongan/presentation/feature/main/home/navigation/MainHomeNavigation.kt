package com.captures2024.soongan.presentation.feature.main.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.captures2024.soongan.core.navigator.screen.main.home.HomeNavigator
import com.captures2024.soongan.presentation.feature.main.home.route.HomeRoute

fun NavGraphBuilder.mainHome(
    navigateToRegistrationPost: () -> Unit,
    navigateToFeed: (NavOptions) -> Unit,
    navigateToPost: (Long, NavOptions?) -> Unit,
) {
    composable<HomeNavigator> {
        val feedNavOption = navOptions {
            popUpTo(HomeNavigator) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        HomeRoute(
            navigateToPost = { navigateToPost(it.postId, null) },
            navigateToFeed = { navigateToFeed(feedNavOption) },
            navigateToRegister = navigateToRegistrationPost,
        )
    }
}
