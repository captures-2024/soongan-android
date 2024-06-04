package com.captures2024.soongan.feature.welcome.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavOptions
import com.captures2024.soongan.feature.welcome.WelcomeUiState
import com.captures2024.soongan.feature.welcome.WelcomeViewModel
import com.captures2024.soongan.feature.welcome.navigation.WELCOME_NAVIGATION_ROUTE
import com.captures2024.soongan.feature.welcome.ui.WelcomeScreen

@Composable
internal fun WelcomeRoute(
    navigateToHome: (NavOptions) -> Unit,
    welcomeViewModel: WelcomeViewModel = hiltViewModel()
) {
    val uiState = welcomeViewModel.uiState.collectAsState()

    if (uiState.value is WelcomeUiState.MoveHome) {
        val navOptions = NavOptions.Builder()
            .setPopUpTo(WELCOME_NAVIGATION_ROUTE, inclusive = true)
            .build()
        navigateToHome(navOptions)
    }

    WelcomeScreen(
        uiState = uiState.value
    )
}