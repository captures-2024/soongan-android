package com.captures2024.soongan.feature.welcome.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.captures2024.soongan.feature.welcome.route.WelcomeRoute

const val WELCOME_NAVIGATION_ROUTE = "welcome_route"

fun NavController.navigateToWelcome(navOptions: NavOptions? = null) {
    this.navigate(WELCOME_NAVIGATION_ROUTE, navOptions)
}

fun NavGraphBuilder.welcome(
    navigateToHome: (NavOptions) -> Unit,
) {
    composable(route = WELCOME_NAVIGATION_ROUTE) {
        WelcomeRoute(
            navigateToHome = navigateToHome,
        )
    }
}