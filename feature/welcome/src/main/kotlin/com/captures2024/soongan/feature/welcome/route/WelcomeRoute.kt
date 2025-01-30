package com.captures2024.soongan.feature.welcome.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.core.viewmodel.welcome.WelcomeViewModel
import com.captures2024.soongan.feature.welcome.ui.WelcomeScreen

@Composable
internal fun WelcomeRoute(
    navigateToHome: () -> Unit,
    welcomeViewModel: WelcomeViewModel = hiltViewModel(),
) {
    val uiState = welcomeViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        welcomeViewModel.sideEffect.collect {
            when (it) {
                is WelcomeViewModel.Effect.NavigateToHome -> navigateToHome()
            }
        }
    }

    WelcomeScreen(
        uiState = uiState.value,
    )
}
