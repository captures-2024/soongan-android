package com.captures2024.soongan.presentation.feature.sign.route

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.captures2024.soongan.core.navigator.screen.sign.SignInNavigator
import com.captures2024.soongan.core.navigator.screen.sign.navigateToSignUp
import com.captures2024.soongan.presentation.feature.signin.navigation.signIn
import com.captures2024.soongan.presentation.feature.signup.navigation.signUp

@Composable
fun SignRoute(
    navController: NavHostController,
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
            navigateToSignUp = { navController.navigateToSignUp() },
        )
        signUp(
            navigateToBack = { navController.navigateUp() },
        )
    }
}
