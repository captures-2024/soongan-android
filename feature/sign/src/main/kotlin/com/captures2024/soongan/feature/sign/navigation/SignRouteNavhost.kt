package com.captures2024.soongan.feature.sign.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.captures2024.soongan.feature.privacypolicy.navigation.navigateToPrivacyPolicy
import com.captures2024.soongan.feature.privacypolicy.navigation.privacyPolicy
import com.captures2024.soongan.feature.sign.route.SignRouteState
import com.captures2024.soongan.feature.signIn.SignInViewModel
import com.captures2024.soongan.feature.signIn.navigation.SIGN_IN_NAVIGATION_ROUTE
import com.captures2024.soongan.feature.signIn.navigation.signIn
import com.captures2024.soongan.feature.termsofuse.navigation.navigateToTermsOfUse
import com.captures2024.soongan.feature.termsofuse.navigation.termsOfUse

@Composable
internal fun SignRouteNavHost(
    modifier: Modifier = Modifier,
    routeState: SignRouteState,
    googleSignIn: () -> Unit,
    signInViewModel: SignInViewModel,
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
            googleSignIn = googleSignIn,
            signInViewModel = signInViewModel,
            navigateToSignUp = {},
            navigateToMain = {},
            navigateToTermsOfUse = navController::navigateToTermsOfUse,
            navigateToPrivacyPolicy = navController::navigateToPrivacyPolicy
        )
        termsOfUse(navigateToBack = navController::popBackStack)
        privacyPolicy(navigateToBack = navController::popBackStack)
    }
}