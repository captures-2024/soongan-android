package com.captures2024.soongan.presentation.feature.main.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.captures2024.soongan.presentation.feature.main.component.screen.MainScreen
import com.captures2024.soongan.presentation.feature.main.navigation.rememberMainNavigationState

@Composable
fun MainRoute(
    isGuestMode: Boolean,
    navController: NavHostController,
) {
    val mainNavigationState = rememberMainNavigationState(
        isGuestMode = isGuestMode,
        navController = navController,
    )

    MainScreen(
        navigationState = mainNavigationState,
    )
}
