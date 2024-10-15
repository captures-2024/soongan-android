package com.captures2024.soongan.feature.welcome.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.captures2024.soongan.core.navigator.screen.main.WelcomeNavigator
import com.captures2024.soongan.feature.welcome.route.WelcomeRoute

fun NavGraphBuilder.welcome(
    navigateToHome: (NavOptions) -> Unit,
) {
    composable<WelcomeNavigator> {
        WelcomeRoute(
            navigateToHome = navigateToHome,
        )
    }
}