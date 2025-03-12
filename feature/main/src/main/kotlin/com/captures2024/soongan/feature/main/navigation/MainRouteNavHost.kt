package com.captures2024.soongan.feature.main.navigation

import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.captures2024.soongan.core.navigator.screen.main.home.HomeNavigator
import com.captures2024.soongan.core.navigator.screen.main.home.navigateToEditPost
import com.captures2024.soongan.core.navigator.screen.main.home.navigateToHome
import com.captures2024.soongan.core.navigator.screen.main.home.navigateToHomeGallery
import com.captures2024.soongan.core.navigator.screen.main.home.navigateToHomePost
import com.captures2024.soongan.core.navigator.screen.main.home.navigateToHomePostPhoto
import com.captures2024.soongan.core.navigator.screen.main.home.navigateToRegistrationPost
import com.captures2024.soongan.core.navigator.screen.main.profile.navigateToEditProfile
import com.captures2024.soongan.core.navigator.screen.main.profile.navigateToFAQ
import com.captures2024.soongan.core.navigator.screen.main.profile.navigateToNotification
import com.captures2024.soongan.core.navigator.screen.main.util.getHidedPostId
import com.captures2024.soongan.core.navigator.screen.main.util.navigateToBackWithHidePost
import com.captures2024.soongan.core.navigator.screen.main.welcome.WelcomeNavigator
import com.captures2024.soongan.feature.awards.navigation.awards
import com.captures2024.soongan.feature.feed.navigation.feed
import com.captures2024.soongan.feature.home.navigation.home
import com.captures2024.soongan.feature.main.route.MainRouteState
import com.captures2024.soongan.feature.profile.navigation.profile
import com.captures2024.soongan.feature.welcome.navigation.welcome

@Composable
internal fun MainRouteNavHost(
    routeState: MainRouteState,
    modifier: Modifier = Modifier,
) {
    val navController = routeState.navController

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = when (routeState.isGuestMode) {
            true -> HomeNavigator
            false -> WelcomeNavigator
        },
        enterTransition = { fadeIn() + scaleIn(initialScale = 0.9f) },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { fadeIn() + scaleIn(initialScale = 0.9f) },
        popExitTransition = { fadeOut() + scaleOut(targetScale = 0.5f) }
    ) {
        welcome(
            navigateToHome = {
                val navOptions = navOptions {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                    restoreState = true
                }

                navController.navigateToHome(navOptions)
            },
        )
        home(
            navigateToBack = navController::popBackStack,
            navigateToRegistrationPost = navController::navigateToRegistrationPost,
            navigateToGallery = navController::navigateToHomeGallery,
            navigateToPost = navController::navigateToHomePost,
            navigateToEditPost = navController::navigateToEditPost,
            navigateToPostPhoto = navController::navigateToHomePostPhoto,
            navigateToBackWithHidePost = navController::navigateToBackWithHidePost,
            getHidedPostId = navController::getHidedPostId,
        )
        feed()
        awards()
        profile(
            navigateToBack = navController::popBackStack,
            navigateToEditProfile = navController::navigateToEditProfile,
            navigateToNotification = navController::navigateToNotification,
            navigateToHomePost = navController::navigateToHomePost,
            navigateToRegistrationPost = navController::navigateToRegistrationPost,
            navigateToFAQ = navController::navigateToFAQ
        )
    }
}
