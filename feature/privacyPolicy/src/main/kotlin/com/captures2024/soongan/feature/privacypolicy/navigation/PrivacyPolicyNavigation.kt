package com.captures2024.soongan.feature.privacypolicy.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.captures2024.soongan.feature.privacypolicy.route.PrivacyPolicyRoute

const val PRIVACY_POLICY_NAVIGATION_ROUTE = "privacy_policy_route"

fun NavController.navigateToPrivacyPolicy(navOptions: NavOptions? = null) {
    this.navigate(PRIVACY_POLICY_NAVIGATION_ROUTE, navOptions)
}

fun NavGraphBuilder.privacyPolicy(
    navigateToBack: () -> Unit,
) {
    composable(route = PRIVACY_POLICY_NAVIGATION_ROUTE) {
        PrivacyPolicyRoute(navigateToBack = navigateToBack)
    }
}