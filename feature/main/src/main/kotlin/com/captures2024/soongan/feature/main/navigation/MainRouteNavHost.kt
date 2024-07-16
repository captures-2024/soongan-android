package com.captures2024.soongan.feature.main.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.captures2024.soongan.feature.awards.navigation.awards
import com.captures2024.soongan.feature.feed.navigation.feed
import com.captures2024.soongan.feature.feed.navigation.navigateToFeed
import com.captures2024.soongan.feature.home.navigation.HOME_NAVIGATION_ROUTE
import com.captures2024.soongan.feature.home.navigation.home
import com.captures2024.soongan.feature.home.navigation.navigateToHome
import com.captures2024.soongan.feature.home.navigation.navigateToHomeGalleryList
import com.captures2024.soongan.feature.home.navigation.navigateToPhotoDetail
import com.captures2024.soongan.feature.home.navigation.navigateToPhotoDetailControl
import com.captures2024.soongan.feature.main.route.MainRouteState
import com.captures2024.soongan.feature.profile.navigation.profile
import com.captures2024.soongan.feature.welcome.navigation.WELCOME_NAVIGATION_ROUTE
import com.captures2024.soongan.feature.welcome.navigation.welcome

@Composable
internal fun MainRouteNavHost(
    modifier: Modifier = Modifier,
    isGuestMode: Boolean,
    routeState: MainRouteState,
    onShowSnackBar: suspend (String) -> Boolean,
) {
    val navController = routeState.navController

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = when (isGuestMode) {
            true -> HOME_NAVIGATION_ROUTE
            false -> WELCOME_NAVIGATION_ROUTE
        },
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ) {
        welcome(
            navigateToHome = navController::navigateToHome,
        )
        home(
            navigateToBack = navController::popBackStack,
            navigateToPhotoList = navController::navigateToHomeGalleryList,
            navigateToPhotoDetail = navController::navigateToPhotoDetail,
            navigateToPhotoDetailControl = navController::navigateToPhotoDetailControl
        )
        feed()
        awards()
        profile()
    }
}

