package com.captures2024.soongan.presentation.feature.main.post.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.captures2024.soongan.core.navigator.screen.main.post.ImageViewerNavigator
import com.captures2024.soongan.core.navigator.screen.main.post.PostInfoNavigator
import com.captures2024.soongan.presentation.feature.main.post.route.ImageViewerRoute
import com.captures2024.soongan.presentation.feature.main.post.route.PostInfoRoute

fun NavGraphBuilder.mainPost(
    navigateToBack: () -> Unit,
    navigateToImageViewer: (String) -> Unit,
    navigateToEditPost: () -> Unit,
    navigateToBackWithHidePost: (Long) -> Unit,
) {
    composable<PostInfoNavigator> {
        PostInfoRoute(
            navigateToBack = navigateToBack,
            navigateToImageViewer = navigateToImageViewer,
            navigateToEditPost = navigateToEditPost,
            navigateToBackWithHidePost = navigateToBackWithHidePost,
        )
    }

    composable<ImageViewerNavigator> {
        ImageViewerRoute(
            navigateToBack = navigateToBack,
        )
    }
}
