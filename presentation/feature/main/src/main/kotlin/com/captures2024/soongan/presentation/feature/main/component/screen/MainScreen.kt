package com.captures2024.soongan.presentation.feature.main.component.screen

import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.captures2024.soongan.core.navigator.screen.main.home.HomeNavigator
import com.captures2024.soongan.core.navigator.screen.main.home.navigateToHome
import com.captures2024.soongan.core.navigator.screen.main.welcome.WelcomeNavigator
import com.captures2024.soongan.presentation.feature.main.awards.navigation.mainAwards
import com.captures2024.soongan.presentation.feature.main.component.MainComponent
import com.captures2024.soongan.presentation.feature.main.feed.navigation.mainFeed
import com.captures2024.soongan.presentation.feature.main.home.navigation.mainHome
import com.captures2024.soongan.presentation.feature.main.navigation.MainNavigationState
import com.captures2024.soongan.presentation.feature.main.navigation.welcome
import com.captures2024.soongan.presentation.feature.main.profile.navigation.mainProfile

@Composable
internal fun MainScreen(navigationState: MainNavigationState) {
    val navController = navigationState.navController

    MainComponent(
        isNotViewBottomBar = navigationState.isNotViewBottomBar(),
        destinations = navigationState.topLevelDestinations,
        onNavigateToDestination = navigationState::navigateToTopLevelDestination,
        currentDestination = navigationState.currentDestination,
    ) { innerPadding ->

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
                navigateToHome = {
                    val navOptions = navigationState.buildTopLevelNavOptions()
                    navController.navigateToHome(navOptions)
                },
            )

            mainAwards()
            mainFeed()
            mainHome()
            mainProfile()
        }
    }
}
