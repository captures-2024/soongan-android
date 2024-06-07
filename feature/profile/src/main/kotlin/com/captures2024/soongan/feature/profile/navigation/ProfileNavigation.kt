package com.captures2024.soongan.feature.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.captures2024.soongan.feature.profile.route.ProfileRoute


const val PROFILE_NAVIGATION_ROUTE = "profile_route"

fun NavController.navigateToProfile(navOptions: NavOptions? = null) {
    this.navigate(PROFILE_NAVIGATION_ROUTE, navOptions)
}

fun NavGraphBuilder.profile() {
    composable(route = PROFILE_NAVIGATION_ROUTE) {
        ProfileRoute()
    }
}