package com.captures2024.soongan.feature.sign.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import com.captures2024.soongan.core.navigator.screen.PrivacyPolicyNavigator
import com.captures2024.soongan.core.navigator.screen.SignInNavigator
import com.captures2024.soongan.core.navigator.screen.SignUpNavigator
import com.captures2024.soongan.core.navigator.screen.TermsOfUseNavigator
import com.captures2024.soongan.feature.privacypolicy.navigation.privacyPolicy
import com.captures2024.soongan.feature.sign.route.SignRouteState
import com.captures2024.soongan.feature.signIn.SignInViewModel
import com.captures2024.soongan.feature.signIn.navigation.signIn
import com.captures2024.soongan.feature.signUp.navigation.navigateToInputBirthYear
import com.captures2024.soongan.feature.signUp.navigation.navigateToInputNickname
import com.captures2024.soongan.feature.signUp.navigation.signUp
import com.captures2024.soongan.feature.termsofuse.navigation.termsOfUse

@Composable
internal fun SignRouteNavHost(
    modifier: Modifier = Modifier,
    routeState: SignRouteState,
    appleSignIn: () -> Unit,
    googleSignIn: () -> Unit,
    kakaoSignIn: () -> Unit,
    navigateToMain: (isGuestMode: Boolean) -> Unit,
    signInViewModel: SignInViewModel,
    onShowSnackBar: suspend (String) -> Boolean,
) {
    val navController = routeState.navController

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = SignInNavigator,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ) {
        signIn(
            appleSignIn = appleSignIn,
            googleSignIn = googleSignIn,
            kakaoSignIn = kakaoSignIn,
            signInViewModel = signInViewModel,
            navigateToMain = navigateToMain,
            navigateToSignUp = navController::navigateToInputNickname,
            navigateToTermsOfUse = navController::navigateToTermsOfUse,
            navigateToPrivacyPolicy = navController::navigateToPrivacyPolicy
        )
        termsOfUse(navigateToBack = navController::popBackStack)
        privacyPolicy(navigateToBack = navController::popBackStack)
        signUp(
            navigateToBack = navController::popBackStack,
            navigateToInputBirthYear = navController::navigateToInputBirthYear
        )
    }
}

fun NavController.navigateToSignIn() = navigate(SignInNavigator)

fun NavController.navigateToSignUp() = navigate(SignUpNavigator)

fun NavController.navigateToTermsOfUse() = navigate(TermsOfUseNavigator)

fun NavController.navigateToPrivacyPolicy() = navigate(PrivacyPolicyNavigator)