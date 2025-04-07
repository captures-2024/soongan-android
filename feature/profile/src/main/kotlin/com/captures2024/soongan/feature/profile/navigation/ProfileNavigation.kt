package com.captures2024.soongan.feature.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.captures2024.soongan.core.model.utils.NotificationSubType
import com.captures2024.soongan.core.navigator.screen.main.profile.FAQNavigator
import com.captures2024.soongan.core.navigator.screen.main.profile.NotificationNavigator
import com.captures2024.soongan.core.navigator.screen.main.profile.ProfileEditNavigator
import com.captures2024.soongan.core.navigator.screen.main.profile.ProfileNavigator
import com.captures2024.soongan.feature.profile.route.EditProfileRoute
import com.captures2024.soongan.feature.profile.route.NotificationRoute
import com.captures2024.soongan.feature.profile.route.ProfileRoute
import com.captures2024.soongan.feature.profile.ui.faq.FAQScreen

fun NavGraphBuilder.profile(
    navigateToBack: () -> Unit,
    navigateToEditProfile: () -> Unit,
    navigateToNotification: () -> Unit,
    navigateByNotification: (NotificationSubType, String?) -> Unit,
    navigateToHomePost: (Long) -> Unit,
    navigateToRegistrationPost: () -> Unit,
    navigateToFAQ: () -> Unit,
) {
    composable<ProfileNavigator> {
        ProfileRoute(
            navigateToEditProfile = navigateToEditProfile,
            navigateToNotification = navigateToNotification,
            navigateToHomePost = { navigateToHomePost(it) },
            navigateToRegistrationPost = navigateToRegistrationPost,
            navigateToFAQ = navigateToFAQ,
        )
    }
    composable<ProfileEditNavigator> {
        EditProfileRoute(
            navigateToBack = navigateToBack,
        )
    }
    composable<NotificationNavigator> {
        NotificationRoute(
            navigateToBack = navigateToBack,
            navigateByNotification = navigateByNotification
        )
    }
    composable<FAQNavigator> {
        FAQScreen(
            navigateToBack = navigateToBack,
        )
    }
}
