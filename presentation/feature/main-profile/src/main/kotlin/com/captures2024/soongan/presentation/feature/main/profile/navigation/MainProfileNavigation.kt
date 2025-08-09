package com.captures2024.soongan.presentation.feature.main.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.captures2024.soongan.core.model.utils.NotificationSubType
import com.captures2024.soongan.core.navigator.screen.main.profile.ExplainNavigator
import com.captures2024.soongan.core.navigator.screen.main.profile.FAQNavigator
import com.captures2024.soongan.core.navigator.screen.main.profile.NotificationNavigator
import com.captures2024.soongan.core.navigator.screen.main.profile.ProfileEditNavigator
import com.captures2024.soongan.core.navigator.screen.main.profile.ProfileNavigator
import com.captures2024.soongan.presentation.feature.main.profile.route.ExplainRoute
import com.captures2024.soongan.presentation.feature.main.profile.route.FaqRoute
import com.captures2024.soongan.presentation.feature.main.profile.route.NotificationRoute
import com.captures2024.soongan.presentation.feature.main.profile.route.ProfileEditRoute
import com.captures2024.soongan.presentation.feature.main.profile.route.ProfileRoute

fun NavGraphBuilder.mainProfile(
    navigateToBack: () -> Unit,
    navigateToNotification: () -> Unit,
    navigateToPostInfo: (Long) -> Unit,
    navigateToRegistrationPost: () -> Unit,
    navigateToEditProfile: () -> Unit,
    navigateToFAQ: () -> Unit,
    navigateFromNotification: (NotificationSubType, String?) -> Unit,
) {
    composable<ProfileNavigator> {
        ProfileRoute(
            navigateToNotification = navigateToNotification,
            navigateToPostInfo = navigateToPostInfo,
            navigateToRegistrationPost = navigateToRegistrationPost,
            navigateToEditProfile = navigateToEditProfile,
            navigateToFAQ = navigateToFAQ,
        )
    }

    composable<ProfileEditNavigator> {
        ProfileEditRoute(
            navigateToBack = navigateToBack,
        )
    }

    composable<NotificationNavigator> {
        NotificationRoute(
            navigateToBack = navigateToBack,
            navigateFromNotification = navigateFromNotification,
        )
    }

    composable<FAQNavigator> {
        FaqRoute(
            navigateToBack = navigateToBack,
        )
    }

    composable<ExplainNavigator> {
        ExplainRoute(
            navigateToBack = navigateToBack,
        )
    }
}
