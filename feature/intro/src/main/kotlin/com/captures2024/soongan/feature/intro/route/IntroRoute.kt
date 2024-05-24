package com.captures2024.soongan.feature.intro.route

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.core.analytics.NetworkMonitor
import com.captures2024.soongan.core.designsystem.component.SoonGanBackground
import com.captures2024.soongan.feature.intro.IntroActivityUiState
import com.captures2024.soongan.feature.intro.IntroState
import com.captures2024.soongan.feature.intro.IntroViewModel
import com.captures2024.soongan.feature.intro.ui.IntroScreen

@Composable
internal fun IntroRoute(
    uiState: IntroActivityUiState,
    networkMonitor: NetworkMonitor,
    routeState: IntroRouteState = rememberIntroRouteState(networkMonitor = networkMonitor),
    navigateToSign: () -> Unit,
    navigateToMain: () -> Unit,
    introViewModel: IntroViewModel = hiltViewModel()
) {
    val state = introViewModel.introState.collectAsStateWithLifecycle().value

    when (state) {
        is IntroState.Sign -> {
            if (uiState is IntroActivityUiState.Success) {
                navigateToSign()
            }
        }
        else -> Unit
    }

    SoonGanBackground {
        IntroScreen(routeState = routeState)
    }
}

