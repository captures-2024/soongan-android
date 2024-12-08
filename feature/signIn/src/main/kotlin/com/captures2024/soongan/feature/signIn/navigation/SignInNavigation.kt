package com.captures2024.soongan.feature.signIn.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.captures2024.soongan.core.navigator.screen.sign.SignInNavigator
import com.captures2024.soongan.core.viewmodel.SignViewModel
import com.captures2024.soongan.feature.signIn.route.SignInRoute


fun NavGraphBuilder.signIn(
    signViewModel: SignViewModel,
    navigateToNickname: () -> Unit,
    navigateToBirthDate: (String) -> Unit,
    navigateToTermsOfUse: () -> Unit,
    navigateToPrivacyPolicy: () -> Unit,
) {
    composable<SignInNavigator> {
        SignInRoute(
            navigateToNickname = navigateToNickname,
            navigateToBirthDate = navigateToBirthDate,
            navigateToTermsOfUse = navigateToTermsOfUse,
            navigateToPrivacyPolicy = navigateToPrivacyPolicy,
            signViewModel = signViewModel
        )
    }
}