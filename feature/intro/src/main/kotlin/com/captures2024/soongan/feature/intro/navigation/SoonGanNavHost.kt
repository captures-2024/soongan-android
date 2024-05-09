package com.captures2024.soongan.feature.intro.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.captures2024.soongan.feature.intro.ui.SoonGanAppState

@Composable
fun SoonGanNavHost(
    modifier: Modifier = Modifier,
    appState: SoonGanAppState,
    onShowSnackBar: suspend (String) -> Boolean,
) {
    val navController = appState.navController

//    NavHost(
//        modifier = modifier,
//        navController = navController,
//        startDestination = SIGN_IN_NAVIGATION_ROUTE,
//        enterTransition = { EnterTransition.None },
//        exitTransition = { ExitTransition.None },
//        popEnterTransition = { EnterTransition.None },
//        popExitTransition = { ExitTransition.None }
//    ) {
//        signIn(
//            navigateToBack = navController::popBackStack,
//            navigateToSignUp = {},
//            navigateToMain = {},
//            navigateToTermsOfUse = navController::navigateToTermsOfUse,
//            navigateToPrivacyPolicy = navController::navigateToPrivacyPolicy
//        )
//    }

}