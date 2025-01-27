package com.captures2024.soongan.feature.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.captures2024.soongan.core.navigator.screen.main.profile.NotificationNavigator
import com.captures2024.soongan.core.navigator.screen.main.profile.ProfileRootNavigator
import com.captures2024.soongan.feature.profile.route.EditProfileRoute
import com.captures2024.soongan.feature.profile.route.NotificationRoute
import com.captures2024.soongan.feature.profile.route.ProfileRoute

fun NavGraphBuilder.profile(
    navigateToBack: () -> Unit,
    navigateToEditProfile: () -> Unit,
    navigateToNotification: () -> Unit,
    navigateToHomePost: (Int) -> Unit,
    navigateToRegistrationPost: () -> Unit,
    navigateToHome: () -> Unit,
) {
    navigation<ProfileRootNavigator>(
        startDestination = ProfileRootNavigator.ProfileNavigator
    ) {
        composable<ProfileRootNavigator.ProfileNavigator> {
            ProfileRoute(
                navigateToEditProfile = navigateToEditProfile,
                navigateToNotification = navigateToNotification,
                navigateToHomePost = { TODO() },
                navigateToRegistrationPost = navigateToRegistrationPost,
                navigateToHome = navigateToHome
            )
        }
        composable<ProfileRootNavigator.EditNavigator> {
            EditProfileRoute(
                navigateToBack = navigateToBack,
            )
        }
        composable<NotificationNavigator> {
            NotificationRoute()
        }
    }
}