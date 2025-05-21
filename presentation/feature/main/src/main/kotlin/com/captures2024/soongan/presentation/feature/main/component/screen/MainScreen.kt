package com.captures2024.soongan.presentation.feature.main.component.screen

import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.captures2024.soongan.core.navigator.screen.main.home.HomeNavigator
import com.captures2024.soongan.core.navigator.screen.main.home.navigateToHome
import com.captures2024.soongan.core.navigator.screen.main.home.navigateToHomeGallery
import com.captures2024.soongan.core.navigator.screen.main.home.navigateToRegistrationPost
import com.captures2024.soongan.core.navigator.screen.main.welcome.WelcomeNavigator
import com.captures2024.soongan.presentation.feature.main.awards.navigation.mainAwards
import com.captures2024.soongan.presentation.feature.main.component.MainComponent
import com.captures2024.soongan.presentation.feature.main.feed.navigation.mainFeed
import com.captures2024.soongan.presentation.feature.main.home.navigation.mainHome
import com.captures2024.soongan.presentation.feature.main.navigation.MainNavigationState
import com.captures2024.soongan.presentation.feature.main.navigation.welcome
import com.captures2024.soongan.presentation.feature.main.profile.navigation.mainProfile
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
internal fun MainScreen(navigationState: MainNavigationState) {
    val scope = rememberCoroutineScope()
    val navController = navigationState.navController

    var isAvailableBack by remember { mutableStateOf(true) }

    val navigateToBack: () -> Unit = {
        if (isAvailableBack) {
            isAvailableBack = false
            navController.navigateUp()

            scope.launch {
                delay(500L)
                isAvailableBack = true
            }
        }
    }

    MainComponent(navigationState = navigationState) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = when (navigationState.isGuestMode) {
                true -> HomeNavigator
                false -> WelcomeNavigator
            },
            enterTransition = { fadeIn() + scaleIn(initialScale = 0.9f) },
            exitTransition = { ExitTransition.None },
            popEnterTransition = { fadeIn() + scaleIn(initialScale = 0.9f) },
            popExitTransition = { fadeOut() + scaleOut(targetScale = 0.5f) },
        ) {
            welcome(
                navigateToHome = navController::navigateToHome,
            )

            mainAwards()
            mainFeed()
            mainHome(
                navigateToBack = navigateToBack,
                navigateToRegistrationPost = navController::navigateToRegistrationPost,
                navigateToGallery = navController::navigateToHomeGallery,
                navigateToPost = { postId, navOptions -> },
            )
            mainProfile()
        }
    }
}
