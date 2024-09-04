package com.captures2024.soongan.feature.home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.captures2024.soongan.core.navigator.screen.main.HomeGalleryNavigator
import com.captures2024.soongan.core.navigator.screen.main.HomeNavigator
import com.captures2024.soongan.core.navigator.screen.main.HomePostNavigator
import com.captures2024.soongan.core.navigator.screen.main.HomePostPhotoNavigator
import com.captures2024.soongan.feature.home.route.HomeGalleryRoute
import com.captures2024.soongan.feature.home.route.HomeRoute

@Composable
fun NavGraphBuilder._home(
    navigateToBack: () -> Unit,
    navigateToGallery: () -> Unit,
    navigateToPost: () -> Unit,
    navigateToPostPhoto: () -> Unit
) {
    composable<HomeNavigator> {
        HomeRoute(
            navigateToPhotoList = { navigateToGallery() },
        )
    }
    composable<HomeGalleryNavigator> {
        HomeGalleryRoute()
    }
    composable<HomePostNavigator> {

    }
    composable<HomePostPhotoNavigator> {

    }
}

