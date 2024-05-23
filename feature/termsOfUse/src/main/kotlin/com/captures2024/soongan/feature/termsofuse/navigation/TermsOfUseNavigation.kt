package com.captures2024.soongan.feature.termsofuse.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.captures2024.soongan.feature.termsofuse.route.TermsOfUseRoute

const val TERMS_OF_USE_NAVIGATION_ROUTE = "terms_of_use_route"

fun NavController.navigateToTermsOfUse(navOptions: NavOptions? = null) {
    this.navigate(TERMS_OF_USE_NAVIGATION_ROUTE, navOptions)
}

fun NavGraphBuilder.termsOfUse(
    navigateToBack: () -> Unit,
) {
    composable(route = TERMS_OF_USE_NAVIGATION_ROUTE) {
        TermsOfUseRoute(navigateToBack = navigateToBack)
    }
}