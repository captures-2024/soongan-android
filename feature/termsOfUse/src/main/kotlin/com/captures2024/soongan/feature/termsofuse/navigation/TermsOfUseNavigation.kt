package com.captures2024.soongan.feature.termsofuse.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.captures2024.soongan.core.navigator.screen.TermsOfUseNavigator
import com.captures2024.soongan.feature.termsofuse.route.TermsOfUseRoute

fun NavGraphBuilder.termsOfUse(
    navigateToBack: () -> Unit,
) {
    composable<TermsOfUseNavigator> {
        TermsOfUseRoute(navigateToBack = navigateToBack)
    }
}