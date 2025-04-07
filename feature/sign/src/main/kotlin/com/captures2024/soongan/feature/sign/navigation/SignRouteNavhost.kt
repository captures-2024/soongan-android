package com.captures2024.soongan.feature.sign.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.captures2024.soongan.core.navigator.screen.sign.SignInNavigator
import com.captures2024.soongan.core.navigator.screen.sign.navigateToBirth
import com.captures2024.soongan.core.navigator.screen.sign.navigateToNickname
import com.captures2024.soongan.core.navigator.screen.sign.navigateToPrivacyPolicy
import com.captures2024.soongan.core.navigator.screen.sign.navigateToTermsOfUse
import com.captures2024.soongan.core.viewmodel.sign.SignViewModel
import com.captures2024.soongan.feature.privacypolicy.navigation.privacyPolicy
import com.captures2024.soongan.feature.signIn.navigation.signIn
import com.captures2024.soongan.feature.signUp.navigation.signUp
import com.captures2024.soongan.feature.termsofuse.navigation.termsOfUse

@Composable
internal fun SignRouteNavHost(
    navController: NavHostController,
    signViewModel: SignViewModel,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = SignInNavigator,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None },
    ) {
        signIn(
            signViewModel = signViewModel,
            navigateToNickname = {
                navController.navigateToNickname()
            },
            navigateToBirth = { nickname ->
                navController.navigateToBirth(nickname = nickname)
            },
            navigateToTermsOfUse = {
                navController.navigateToTermsOfUse()
            },
            navigateToPrivacyPolicy = {
                navController.navigateToPrivacyPolicy()
            },
        )
        termsOfUse(
            navigateToBack = {
                navController.popBackStack()
            },
        )
        privacyPolicy(
            navigateToBack = {
                navController.popBackStack()
            },
        )
        signUp(
            navigateToBack = {
                navController.popBackStack()
            },
            navigateToBirth = { nickname ->
                navController.navigateToBirth(nickname)
            },
        )
    }
}
