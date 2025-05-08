package com.captures2024.soongan.presentation.feature.sign_up.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.captures2024.soongan.core.navigator.screen.sign.SignUpNavigator
import com.captures2024.soongan.presentation.feature.sign_up.route.SignUpRoute

fun NavGraphBuilder.signUp(
    navigateToBack: () -> Unit,
) {
    composable<SignUpNavigator> {
        SignUpRoute(
            navigateToBack = navigateToBack,
        )
    }
}
