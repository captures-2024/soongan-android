package com.captures2024.soongan.presentation.feature.sign_in.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.captures2024.soongan.core.navigator.screen.sign.SignInNavigator
import com.captures2024.soongan.presentation.feature.sign_in.route.SignInRoute

fun NavGraphBuilder.signIn(
    navigateToSignUp: () -> Unit,
) {
    composable<SignInNavigator> {
        SignInRoute(
            navigateToSignUp = navigateToSignUp,
        )
    }
}
