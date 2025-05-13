package com.captures2024.soongan.presentation.feature.main.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.captures2024.soongan.core.navigator.screen.main.welcome.WelcomeNavigator
import com.captures2024.soongan.presentation.feature.main.route.WelcomeRoute

fun NavGraphBuilder.welcome(
    navigateToHome: () -> Unit,
) {
    composable<WelcomeNavigator> {
        WelcomeRoute(
            navigateToHome = navigateToHome,
        )
    }
}