package com.captures2024.soongan.presentation.feature.root.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.captures2024.soongan.presentation.feature.sign.route.SignRoute

@Composable
internal fun AppSignRoute(navController: NavHostController) {
    SignRoute(
        navController = navController,
    )
}
