package com.captures2024.soongan.presentation.feature.root.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.captures2024.soongan.presentation.feature.main.route.MainRoute

@Composable
internal fun AppMainRoute(
    isGuestMode: Boolean,
    navController: NavHostController,
) {
    MainRoute(
        isGuestMode = isGuestMode,
        navController = navController,
    )
}
