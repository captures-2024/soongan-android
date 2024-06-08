package com.captures2024.soongan.feature.feed.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.captures2024.soongan.feature.feed.route.FeedRoute

const val FEED_NAVIGATION_ROUTE = "feed_route"

fun NavController.navigateToFeed(navOptions: NavOptions? = null) {
    this.navigate(FEED_NAVIGATION_ROUTE, navOptions)
}

fun NavGraphBuilder.feed() {
    composable(route = FEED_NAVIGATION_ROUTE) {
        FeedRoute()
    }
}