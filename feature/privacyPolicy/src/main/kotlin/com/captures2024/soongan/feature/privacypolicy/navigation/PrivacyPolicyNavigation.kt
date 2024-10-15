package com.captures2024.soongan.feature.privacypolicy.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.captures2024.soongan.core.navigator.screen.sign.PrivacyPolicyNavigator
import com.captures2024.soongan.feature.privacypolicy.route.PrivacyPolicyRoute

fun NavGraphBuilder.privacyPolicy(
    navigateToBack: () -> Unit,
) {
    composable<PrivacyPolicyNavigator> {
        PrivacyPolicyRoute(navigateToBack = navigateToBack)
    }
}