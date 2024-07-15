package com.captures2024.soongan.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.captures2024.soongan.feature.home.route.HomeRoute
import com.captures2024.soongan.feature.home.route.PhotoDetailRoute

const val HOME_NAVIGATION_ROUTE = "home_route"
const val PHOTO_DETAIL_NAVIGATION_ROUTE = "photo_detail_route"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(HOME_NAVIGATION_ROUTE, navOptions)
}

fun NavController.navigateToPhotoDetail(navOptions: NavOptions? = null) {
    this.navigate(PHOTO_DETAIL_NAVIGATION_ROUTE, navOptions)
}

fun NavGraphBuilder.home() {
    composable(route = HOME_NAVIGATION_ROUTE) {
        HomeRoute(
            navigateToFeed = navigateToFeed,
        )
    }
    composable(route = PHOTO_DETAIL_NAVIGATION_ROUTE) {
        PhotoDetailRoute()
    }
}