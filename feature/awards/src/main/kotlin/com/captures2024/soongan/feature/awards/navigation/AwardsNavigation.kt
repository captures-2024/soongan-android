package com.captures2024.soongan.feature.awards.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val AWARDS_NAVIGATION_ROUTE = "awards_route"

fun NavController.navigateToAwards(navOptions: NavOptions? = null) {
    this.navigate(AWARDS_NAVIGATION_ROUTE, navOptions)
}

fun NavGraphBuilder.awards() {
    composable(route = AWARDS_NAVIGATION_ROUTE) {

    }
}