package com.captures2024.soongan.feature.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.core.model.UserProfile
import com.captures2024.soongan.core.navigator.screen.main.profile.EditProfileNavigator
import com.captures2024.soongan.core.navigator.screen.main.profile.NotificationNavigator
import com.captures2024.soongan.core.navigator.screen.main.profile.ProfileNavigator
import com.captures2024.soongan.feature.profile.route.EditProfileRoute
import com.captures2024.soongan.feature.profile.route.NotificationRoute
import com.captures2024.soongan.feature.profile.route.ProfileRoute

fun NavGraphBuilder.profile(
    navigateToBack: () -> Unit,
    navigateToEditProfile: (userProfile: UserProfile) -> Unit,
    navigateToNotification: () -> Unit,
    navigateToHomePost: (userPhoto: UserPost.PhotoPost) -> Unit,
) {
    composable<ProfileNavigator> {
        ProfileRoute(
            navigateToEditProfile = navigateToEditProfile,
            navigateToNotification = navigateToNotification,
            navigateToHomePost = navigateToHomePost
        )
    }
    composable<EditProfileNavigator> {
        EditProfileRoute(
            navigateToBack = navigateToBack
        )
    }
    composable<NotificationNavigator> {
        NotificationRoute()
    }
}