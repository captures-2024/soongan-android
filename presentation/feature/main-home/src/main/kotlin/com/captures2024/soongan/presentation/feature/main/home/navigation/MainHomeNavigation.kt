package com.captures2024.soongan.presentation.feature.main.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.captures2024.soongan.core.navigator.screen.main.home.HomeNavigator
import com.captures2024.soongan.presentation.feature.main.home.route.HomeRoute

fun NavGraphBuilder.mainHome() {
    composable<HomeNavigator> {
        HomeRoute()
    }
}
