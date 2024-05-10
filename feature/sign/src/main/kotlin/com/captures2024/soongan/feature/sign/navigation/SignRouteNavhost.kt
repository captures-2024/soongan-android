package com.captures2024.soongan.feature.sign.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.captures2024.soongan.feature.sign.ui.SignRouteState
import com.captures2024.soongan.feature.signIn.navigation.SIGN_IN_NAVIGATION_ROUTE
import com.captures2024.soongan.feature.signIn.navigation.navigateToPrivacyPolicy
import com.captures2024.soongan.feature.signIn.navigation.navigateToTermsOfUse
import com.captures2024.soongan.feature.signIn.navigation.signIn

@Composable
internal fun SignRouteNavHost(
    modifier: Modifier = Modifier,
    routeState: SignRouteState,
    onShowSnackBar: suspend (String) -> Boolean,
) {
    val navController = routeState.navController

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = SIGN_IN_NAVIGATION_ROUTE,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ) {
        signIn(
            navigateToBack = navController::popBackStack,
            navigateToSignUp = {},
            navigateToMain = {},
            navigateToTermsOfUse = navController::navigateToTermsOfUse,
            navigateToPrivacyPolicy = navController::navigateToPrivacyPolicy
        )
    }
}