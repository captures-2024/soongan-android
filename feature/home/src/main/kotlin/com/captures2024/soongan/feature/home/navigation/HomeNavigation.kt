package com.captures2024.soongan.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.captures2024.soongan.core.navigator.screen.main.home.HomeGalleryNavigator
import com.captures2024.soongan.core.navigator.screen.main.home.HomeNavigator
import com.captures2024.soongan.core.navigator.screen.main.home.HomePostNavigator
import com.captures2024.soongan.core.navigator.screen.main.home.HomePostPhotoNavigator
import com.captures2024.soongan.core.navigator.screen.main.home.RegistrationPostNavigator
import com.captures2024.soongan.feature.home.route.HomeGalleryRoute
import com.captures2024.soongan.feature.home.route.HomePostPhotoRoute
import com.captures2024.soongan.feature.home.route.HomePostRoute
import com.captures2024.soongan.feature.home.route.HomeRoute
import com.captures2024.soongan.feature.home.route.RegistrationPostRoute

fun NavGraphBuilder.home(
    navigateToBack: () -> Unit,
    navigateToRegistrationPost: () -> Unit,
    navigateToGallery: () -> Unit,
    navigateToPost: (Long, NavOptions?) -> Unit,
    navigateToPostPhoto: (String) -> Unit,
    setReportedPostId: (postId: Long) -> Unit,
    getReportedPostId: () -> Long,
) {
    composable<HomeNavigator> {
        HomeRoute(
            navigateToRegistrationPost = navigateToRegistrationPost,
            navigateToGallery = navigateToGallery,
            navigateToPost = navigateToPost,
        )
    }
    composable<RegistrationPostNavigator> {
        RegistrationPostRoute(
            navigateToBack = navigateToBack,
            navigateToPost = navigateToPost,
        )
    }
    composable<HomeGalleryNavigator> {
        HomeGalleryRoute(
            navigateToBack = navigateToBack,
            navigateToPost = navigateToPost,
            navigateToRegistrationPost = navigateToRegistrationPost,
            getReportedPostId = getReportedPostId,
        )
    }
    composable<HomePostNavigator> {
        HomePostRoute(
            navigateToBack = navigateToBack,
            navigateToHomePostPhoto = navigateToPostPhoto,
            setReportedPostId = setReportedPostId,
        )
    }
    composable<HomePostPhotoNavigator> {
        HomePostPhotoRoute(
            navigateToBack = navigateToBack
        )
    }
}

