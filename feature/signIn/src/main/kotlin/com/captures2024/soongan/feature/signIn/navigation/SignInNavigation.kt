package com.captures2024.soongan.feature.signIn.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.captures2024.soongan.core.navigator.screen.SignInNavigator
import com.captures2024.soongan.feature.signIn.SignInViewModel
import com.captures2024.soongan.feature.signIn.route.SignInRoute


fun NavGraphBuilder.signIn(
    appleSignIn: () -> Unit,
    googleSignIn: () -> Unit,
    kakaoSignIn: () -> Unit,
    signInViewModel: SignInViewModel,
    navigateToMain: (Boolean) -> Unit,
    navigateToSignUp: (NavOptions) -> Unit,
    navigateToTermsOfUse: () -> Unit,
    navigateToPrivacyPolicy: () -> Unit,
) {
    composable<SignInNavigator> {
        SignInRoute(
            appleSignIn = appleSignIn,
            googleSignIn = googleSignIn,
            kakaoSignIn = kakaoSignIn,
            navigateToMain = navigateToMain,
            navigateToSignUp = navigateToSignUp,
            navigateToTermsOfUse = navigateToTermsOfUse,
            navigateToPrivacyPolicy = navigateToPrivacyPolicy,
            signInViewModel = signInViewModel
        )
    }
}