package com.captures2024.soongan.feature.welcome.route

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import com.captures2024.soongan.core.navigator.screen.main.WelcomeNavigator
import com.captures2024.soongan.feature.welcome.WelcomeUiState
import com.captures2024.soongan.feature.welcome.WelcomeViewModel
import com.captures2024.soongan.feature.welcome.ui.WelcomeScreen

@Composable
internal fun WelcomeRoute(
    navigateToHome: (NavOptions) -> Unit,
    welcomeViewModel: WelcomeViewModel = hiltViewModel(),
) {
    val uiState = welcomeViewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.value is WelcomeUiState.MoveHome) {
        val navOptions = navOptions {
            popUpTo<WelcomeNavigator>()
        }
        navigateToHome(navOptions)
    }

    WelcomeScreen(
        uiState = uiState.value,
    )
}
