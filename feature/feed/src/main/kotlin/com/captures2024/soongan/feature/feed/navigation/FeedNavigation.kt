package com.captures2024.soongan.feature.feed.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.captures2024.soongan.core.navigator.screen.main.feed.FeedNavigator
import com.captures2024.soongan.feature.feed.route.FeedRoute

fun NavGraphBuilder.feed(
    navigateToPost: (Long, NavOptions?) -> Unit,
    getHideTargetContentId: () -> Long,
) {
    composable<FeedNavigator> {
        FeedRoute(
            navigateToPost = navigateToPost,
            getHideTargetContentId = getHideTargetContentId,
        )
    }
}
