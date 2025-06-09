package com.captures2024.soongan.presentation.feature.main.post.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.captures2024.soongan.core.navigator.screen.main.post.EditPostNavigator
import com.captures2024.soongan.core.navigator.screen.main.post.ImageViewerNavigator
import com.captures2024.soongan.core.navigator.screen.main.post.PostInfoNavigator
import com.captures2024.soongan.presentation.feature.main.post.route.ImageViewerRoute
import com.captures2024.soongan.presentation.feature.main.post.route.PostInfoEditRoute
import com.captures2024.soongan.presentation.feature.main.post.route.PostInfoRoute

fun NavGraphBuilder.mainPost(
    navigateToBack: () -> Unit,
    navigateToImageViewer: (String) -> Unit,
    navigateToEditPost: (Long, String, String) -> Unit,
) {
    composable<PostInfoNavigator> {
        PostInfoRoute(
            navigateToBack = navigateToBack,
            navigateToImageViewer = navigateToImageViewer,
            navigateToEditPost = navigateToEditPost,
        )
    }

    composable<ImageViewerNavigator> {
        ImageViewerRoute(
            navigateToBack = navigateToBack,
        )
    }

    composable<EditPostNavigator> {
        PostInfoEditRoute(
            navigateToBack = navigateToBack,
        )
    }
}
