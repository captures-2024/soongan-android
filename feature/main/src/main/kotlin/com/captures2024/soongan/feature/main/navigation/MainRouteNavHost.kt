package com.captures2024.soongan.feature.main.navigation

import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import com.captures2024.soongan.core.model.dto.PostInfoDto
import com.captures2024.soongan.core.navigator.screen.main.awards.AwardsNavigator
import com.captures2024.soongan.core.navigator.screen.main.feed.FeedNavigator
import com.captures2024.soongan.core.navigator.screen.main.home.HomeGalleryNavigator
import com.captures2024.soongan.core.navigator.screen.main.home.HomeNavigator
import com.captures2024.soongan.core.navigator.screen.main.home.HomePostNavigator
import com.captures2024.soongan.core.navigator.screen.main.home.HomePostPhotoNavigator
import com.captures2024.soongan.core.navigator.screen.main.home.RegistrationPostNavigator
import com.captures2024.soongan.core.navigator.screen.main.profile.NotificationNavigator
import com.captures2024.soongan.core.navigator.screen.main.profile.ProfileRootNavigator
import com.captures2024.soongan.core.navigator.screen.main.welcome.WelcomeNavigator
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
    nickname: String,
) {
    val navController = routeState.navController

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = when (isGuestMode) {
            true -> HomeNavigator
            false -> WelcomeNavigator(
                nickname = nickname,
            )
        },
        enterTransition = { fadeIn() + scaleIn(initialScale = 0.9f) },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { fadeIn() + scaleIn(initialScale = 0.9f) },
        popExitTransition = { fadeOut() + scaleOut(targetScale = 0.5f) }
    ) {
        welcome(
            navigateToHome = navController::navigateToHome,
        )
        home(
            navigateToBack = navController::popBackStack,
            navigateToRegistrationPost = navController::navigateToRegistrationPost,
            navigateToGallery = navController::navigateToHomeGallery,
            navigateToPost = navController::navigateToHomePost,
            navigateToPostPhoto = navController::navigateToHomePostPhoto
        )
        feed()
        awards()
        profile(
            navController = navController,
            navigateToBack = navController::popBackStack,
            navigateToEditProfile = navController::navigateToEditProfile,
            navigateToNotification = navController::navigateToNotification,
            navigateToHomePost = navController::navigateToHomePost,
            navigateToRegistrationPost = navController::navigateToRegistrationPost
        )
    }
}

internal fun NavController.navigateToHome() = navigate(HomeNavigator)
internal fun NavController.navigateToHome(navOptions: NavOptions) =
    navigate(HomeNavigator, navOptions)

internal fun NavController.navigateToRegistrationPost() = navigate(RegistrationPostNavigator)
internal fun NavController.navigateToRegistrationPost(navOptions: NavOptions) = navigate(
    RegistrationPostNavigator, navOptions
)

internal fun NavController.navigateToHomeGallery() = navigate(HomeGalleryNavigator)
internal fun NavController.navigateToHomeGallery(navOptions: NavOptions) =
    navigate(HomeGalleryNavigator, navOptions)

internal fun NavController.navigateToHomePost(
    post: PostInfoDto,
) = navigate(
    HomePostNavigator(
        id = post.postId,
        url = post.imageUrl,
    ),
)

internal fun NavController.navigateToHomePost(
    post: PostInfoDto,
    navOptions: NavOptions,
) = navigate(
    HomePostNavigator(
        id = post.postId,
        url = post.imageUrl,
    ),
    navOptions = navOptions
)

internal fun NavController.navigateToHomePostPhoto(url: String) =
    navigate(HomePostPhotoNavigator(url))

internal fun NavController.navigateToHomePostPhoto(
    url: String,
    navOptions: NavOptions,
) = navigate(HomePostPhotoNavigator(url), navOptions)

internal fun NavController.navigateToFeed() = navigate(FeedNavigator)
internal fun NavController.navigateToFeed(navOptions: NavOptions) =
    navigate(FeedNavigator, navOptions)

internal fun NavController.navigateToAwards() = navigate(AwardsNavigator)
internal fun NavController.navigateToAwards(navOptions: NavOptions) =
    navigate(AwardsNavigator, navOptions)

internal fun NavController.navigateToProfile() = navigate(ProfileRootNavigator)
internal fun NavController.navigateToProfile(navOptions: NavOptions) =
    navigate(ProfileRootNavigator, navOptions)

internal fun NavController.navigateToEditProfile() =
    navigate(ProfileRootNavigator.EditNavigator)

internal fun NavController.navigateToEditProfile(navOptions: NavOptions) =
    navigate(ProfileRootNavigator.EditNavigator, navOptions)

internal fun NavController.navigateToNotification() = navigate(NotificationNavigator)
internal fun NavController.navigateToNotification(navOptions: NavOptions) =
    navigate(NotificationNavigator, navOptions)