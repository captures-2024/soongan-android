package com.captures2024.soongan.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.core.navigator.screen.main.HomeGalleryNavigator
import com.captures2024.soongan.core.navigator.screen.main.HomeNavigator
import com.captures2024.soongan.core.navigator.screen.main.HomePostNavigator
import com.captures2024.soongan.core.navigator.screen.main.HomePostPhotoNavigator
import com.captures2024.soongan.feature.home.route.HomeGalleryRoute
import com.captures2024.soongan.feature.home.route.HomePostPhotoRoute
import com.captures2024.soongan.feature.home.route.HomePostRoute
import com.captures2024.soongan.feature.home.route.HomeRoute

fun NavGraphBuilder.home(
    navigateToBack: () -> Unit,
    navigateToGallery: () -> Unit,
    navigateToPost: (UserPost.PhotoPost) -> Unit,
    navigateToPostPhoto: (String) -> Unit
) {
    composable<HomeNavigator> {
        HomeRoute {
            navigateToGallery()
        }
//        _HomeRoute(
//            navigateToHomeGallery = navigateToGallery,
//        )
    }
    composable<HomeGalleryNavigator> {
        HomeGalleryRoute(
            navigateToBack = navigateToBack,
            navigateToPost = navigateToPost
        )
    }
    composable<HomePostNavigator> {
        HomePostRoute(
            navigateToBack = navigateToBack,
            navigateToHomePostPhoto = navigateToPostPhoto
        )
    }
    composable<HomePostPhotoNavigator> {
        HomePostPhotoRoute(
            navigateToBack = navigateToBack
        )
    }
}

