package com.captures2024.soongan.presentation.feature.main.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.captures2024.soongan.presentation.feature.main.component.screen.WelcomeScreen
import com.captures2024.soongan.presentation.viewmodel.main.WelcomeViewModel

@Composable
internal fun WelcomeRoute(
    navigateToHome: () -> Unit,
    welcomeViewModel: WelcomeViewModel = hiltViewModel(),
) {
    val state by welcomeViewModel.state.collectAsState()

    LaunchedEffect(welcomeViewModel.sideEffect) {
        welcomeViewModel.sideEffect.collect { effect ->
            when (effect) {
                is WelcomeViewModel.Effect.NavigateToHome -> navigateToHome()
            }
        }
    }

    WelcomeScreen(
        state = state,
    )
}