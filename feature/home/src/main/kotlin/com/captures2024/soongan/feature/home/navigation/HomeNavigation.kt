package com.captures2024.soongan.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.captures2024.soongan.feature.home.route.HomeRoute

const val HOME_NAVIGATION_ROUTE = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(HOME_NAVIGATION_ROUTE, navOptions)
}

fun NavGraphBuilder.home(
    navigateToFeed: () -> Unit,
) {
    composable(route = HOME_NAVIGATION_ROUTE) {
        HomeRoute(
            navigateToFeed = navigateToFeed,
        )
    }
}