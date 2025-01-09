package com.captures2024.soongan.feature.profile.navigation

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.captures2024.soongan.core.model.dto.PostInfoDto
import com.captures2024.soongan.core.navigator.screen.main.profile.NotificationNavigator
import com.captures2024.soongan.core.navigator.screen.main.profile.ProfileRootNavigator
import com.captures2024.soongan.feature.profile.ProfileViewModel
import com.captures2024.soongan.feature.profile.route.EditProfileRoute
import com.captures2024.soongan.feature.profile.route.NotificationRoute
import com.captures2024.soongan.feature.profile.route.ProfileRoute

fun NavGraphBuilder.profile(
    navController: NavController,
    navigateToBack: () -> Unit,
    navigateToEditProfile: () -> Unit,
    navigateToNotification: () -> Unit,
    navigateToHomePost: (postInfo: PostInfoDto) -> Unit,
) {
    navigation<ProfileRootNavigator>(
        startDestination = ProfileRootNavigator.ProfileNavigator
    ) {
        composable<ProfileRootNavigator.ProfileNavigator> { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(ProfileRootNavigator)
            }
            val profileViewModel = hiltViewModel<ProfileViewModel>(parentEntry)

            ProfileRoute(
                navigateToEditProfile = navigateToEditProfile,
                navigateToNotification = navigateToNotification,
                navigateToHomePost = { TODO() },
                profileViewModel = profileViewModel,
            )
        }
        composable<ProfileRootNavigator.EditNavigator> { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(ProfileRootNavigator)
            }
            val profileViewModel = hiltViewModel<ProfileViewModel>(parentEntry)

            EditProfileRoute(
                navigateToBack = navigateToBack,
                profileViewModel = profileViewModel,
            )
        }
        composable<NotificationNavigator> {
            NotificationRoute()
        }
    }
}