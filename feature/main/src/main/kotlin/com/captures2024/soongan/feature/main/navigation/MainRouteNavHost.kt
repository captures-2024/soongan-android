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
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.core.model.UserProfile
import com.captures2024.soongan.core.navigator.screen.main.awards.AwardsNavigator
import com.captures2024.soongan.core.navigator.screen.main.feed.FeedNavigator
import com.captures2024.soongan.core.navigator.screen.main.home.HomeGalleryNavigator
import com.captures2024.soongan.core.navigator.screen.main.home.HomeNavigator
import com.captures2024.soongan.core.navigator.screen.main.home.HomePostNavigator
import com.captures2024.soongan.core.navigator.screen.main.home.HomePostPhotoNavigator
import com.captures2024.soongan.core.navigator.screen.main.home.RegistrationPostNavigator
import com.captures2024.soongan.core.navigator.screen.main.profile.EditProfileNavigator
import com.captures2024.soongan.core.navigator.screen.main.profile.NotificationNavigator
import com.captures2024.soongan.core.navigator.screen.main.profile.ProfileNavigator
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
            navigateToBack = navController::popBackStack,
            navigateToEditProfile = navController::navigateToEditProfile,
            navigateToNotification = navController::navigateToNotification,
            navigateToHomePost = navController::navigateToHomePost
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
    post: UserPost.PhotoPost,
) = navigate(
    HomePostNavigator(
        id = post.id,
        url = post.url,
        title = post.title
    )
)

internal fun NavController.navigateToHomePost(
    post: UserPost.PhotoPost,
    navOptions: NavOptions,
) = navigate(
    HomePostNavigator(
        id = post.id,
        url = post.url,
        title = post.title
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

internal fun NavController.navigateToProfile() = navigate(ProfileNavigator)
internal fun NavController.navigateToProfile(navOptions: NavOptions) =
    navigate(ProfileNavigator, navOptions)

internal fun NavController.navigateToEditProfile(
    userProfile: UserProfile,
) = navigate(
    EditProfileNavigator(
        profileImageUrl = userProfile.profileImageUrl,
        nickname = userProfile.nickname,
        selfIntroduction = userProfile.selfIntroduction
    )
)

internal fun NavController.navigateToEditProfile(
    userProfile: UserProfile,
    navOptions: NavOptions,
) = navigate(
    EditProfileNavigator(
        profileImageUrl = userProfile.profileImageUrl,
        nickname = userProfile.nickname,
        selfIntroduction = userProfile.selfIntroduction
    ), navOptions
)

internal fun NavController.navigateToNotification() = navigate(NotificationNavigator)
internal fun NavController.navigateToNotification(navOptions: NavOptions) =
    navigate(NotificationNavigator, navOptions)