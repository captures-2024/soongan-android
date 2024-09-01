package com.captures2024.soongan.feature.intro.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.captures2024.soongan.core.analytics.NetworkMonitor
import com.captures2024.soongan.core.designsystem.component.SoonGanBackground
import com.captures2024.soongan.feature.intro.IntroViewModel
import com.captures2024.soongan.feature.intro.state.IntroSideEffect
import com.captures2024.soongan.feature.intro.ui.IntroScreen

@Composable
internal fun IntroRoute(
    networkMonitor: NetworkMonitor,
    routeState: IntroRouteState = rememberIntroRouteState(networkMonitor = networkMonitor),
    navigateToSign: () -> Unit,
    navigateToMain: () -> Unit,
    introViewModel: IntroViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        introViewModel.sideEffect.collect {
            when (it) {
                is IntroSideEffect.NavigateToSign -> navigateToSign()
                is IntroSideEffect.NavigateToMain -> navigateToMain()
            }
        }
    }

    SoonGanBackground {
        IntroScreen(routeState = routeState)
    }
}

