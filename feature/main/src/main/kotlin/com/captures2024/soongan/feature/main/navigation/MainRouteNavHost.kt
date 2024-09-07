package com.captures2024.soongan.feature.main.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.core.navigator.screen.main.AwardsNavigator
import com.captures2024.soongan.core.navigator.screen.main.FeedNavigator
import com.captures2024.soongan.core.navigator.screen.main.HomeGalleryNavigator
import com.captures2024.soongan.core.navigator.screen.main.HomeNavigator
import com.captures2024.soongan.core.navigator.screen.main.HomePostNavigator
import com.captures2024.soongan.core.navigator.screen.main.HomePostPhotoNavigator
import com.captures2024.soongan.core.navigator.screen.main.ProfileNavigator
import com.captures2024.soongan.core.navigator.screen.main.WelcomeNavigator
import com.captures2024.soongan.feature.awards.navigation.awards
import com.captures2024.soongan.feature.feed.navigation.feed
import com.captures2024.soongan.feature.home.navigation.home
import com.captures2024.soongan.feature.main.route.MainRouteState
import com.captures2024.soongan.feature.profile.navigation.profile
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
            true -> HomeNavigator
            false -> WelcomeNavigator
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
            navigateToGallery = navController::navigateToHomeGallery,
            navigateToPost = navController::navigateToHomePost,
            navigateToPostPhoto = navController::navigateToHomePostPhoto
        )
        feed()
        awards()
        profile()
    }
}

internal fun NavController.navigateToHome() = navigate(HomeNavigator)
internal fun NavController.navigateToHome(navOptions: NavOptions) = navigate(HomeNavigator, navOptions)

internal fun NavController.navigateToHomeGallery() = navigate(HomeGalleryNavigator)
internal fun NavController.navigateToHomeGallery(navOptions: NavOptions) = navigate(HomeGalleryNavigator, navOptions)

internal fun NavController.navigateToHomePost(
    post: UserPost.PhotoPost
) = navigate(
    HomePostNavigator(
        id = post.id,
        url = post.url,
        title = post.title
    )
)
internal fun NavController.navigateToHomePost(
    post: UserPost.PhotoPost,
    navOptions: NavOptions
) = navigate(
    HomePostNavigator(
        id = post.id,
        url = post.url,
        title = post.title
    ),
    navOptions = navOptions
)

internal fun NavController.navigateToHomePostPhoto(url: String) = navigate(HomePostPhotoNavigator(url))
internal fun NavController.navigateToHomePostPhoto(
    url: String,
    navOptions: NavOptions
) = navigate(HomePostPhotoNavigator(url), navOptions)

internal fun NavController.navigateToFeed() = navigate(FeedNavigator)
internal fun NavController.navigateToFeed(navOptions: NavOptions) = navigate(FeedNavigator, navOptions)

internal fun NavController.navigateToAwards() = navigate(AwardsNavigator)
internal fun NavController.navigateToAwards(navOptions: NavOptions) = navigate(AwardsNavigator, navOptions)

internal fun NavController.navigateToProfile() = navigate(ProfileNavigator)
internal fun NavController.navigateToProfile(navOptions: NavOptions) = navigate(ProfileNavigator, navOptions)