package com.captures2024.soongan.feature.home.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavOptions
import com.captures2024.soongan.feature.home.ui.HomeScreen

@Composable
internal fun HomeRoute(
    navigateToPhotoList: (NavOptions?) -> Unit,
) {
    HomeScreen(
        navigateToPhotoList = navigateToPhotoList
    )
}