package com.captures2024.soongan.presentation.feature.main.feed.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.captures2024.soongan.core.navigator.screen.main.feed.FeedNavigator
import com.captures2024.soongan.presentation.feature.main.feed.route.FeedRoute

fun NavGraphBuilder.mainFeed(
    navigateToPost: (Long, NavOptions?) -> Unit,
) {
    composable<FeedNavigator> {
        FeedRoute(
            navigateToPost = navigateToPost,
        )
    }
}
