package com.captures2024.soongan.feature.signIn.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.captures2024.soongan.feature.signIn.route.SignInRoute

const val SIGN_IN_NAVIGATION_ROUTE = "sign_in_route"

fun NavController.navigateToSignIn(navOptions: NavOptions? = null) {
    this.navigate(SIGN_IN_NAVIGATION_ROUTE, navOptions)
}

fun NavGraphBuilder.signIn(
    navigateToSignUp: (NavOptions) -> Unit,
    navigateToMain: (NavOptions) -> Unit,
    navigateToTermsOfUse: (NavOptions) -> Unit,
    navigateToPrivacyPolicy: (NavOptions) -> Unit,
) {
    composable(route = SIGN_IN_NAVIGATION_ROUTE) {
        SignInRoute(
            navigateToTermsOfUse = navigateToTermsOfUse,
            navigateToPrivacyPolicy = navigateToPrivacyPolicy,
        )
    }
}