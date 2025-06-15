package com.captures2024.soongan.presentation.feature.main.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.captures2024.soongan.core.navigator.screen.main.home.HomeGalleryNavigator
import com.captures2024.soongan.core.navigator.screen.main.home.HomeNavigator
import com.captures2024.soongan.presentation.feature.main.home.route.HomeGalleryRoute
import com.captures2024.soongan.presentation.feature.main.home.route.HomeRoute

fun NavGraphBuilder.mainHome(
    navigateToBack: () -> Unit,
    navigateToRegistrationPost: () -> Unit,
    navigateToGallery: () -> Unit,
    navigateToPost: (Long, NavOptions?) -> Unit,
) {
    composable<HomeNavigator> {
        HomeRoute(
            navigateToPost = { navigateToPost(it.postId, null) },
            navigateToPostList = navigateToGallery,
            navigateToRegister = navigateToRegistrationPost,
        )
    }

    composable<HomeGalleryNavigator> {
        HomeGalleryRoute(
            navigateToBack = navigateToBack,
            navigateToPost = { navigateToPost(it, null) },
            navigateToRegistrationPost = navigateToRegistrationPost,
        )
    }
}
