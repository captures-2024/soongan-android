package com.captures2024.soongan.feature.feed.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.captures2024.soongan.core.navigator.screen.main.FeedNavigator
import com.captures2024.soongan.feature.feed.route.FeedRoute

fun NavGraphBuilder.feed() {
    composable<FeedNavigator> {
        FeedRoute()
    }
}
