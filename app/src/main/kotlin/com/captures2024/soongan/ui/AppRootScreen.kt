package com.captures2024.soongan.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.captures2024.soongan.feature.intro.route.IntroRoute
import com.captures2024.soongan.state.AppRootRouteState
import com.captures2024.soongan.state.AppRootUIState

@Composable
internal fun AppRootScreen(
    uiState: AppRootUIState,
) {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center,
    ) {
        when (uiState.rootRouteState) {
            AppRootRouteState.LANDING -> AppLandingRoute()

            AppRootRouteState.SIGN -> AppSignRoute()

            AppRootRouteState.MAIN -> AppMainRoute()
        }
    }
}

@Composable
private fun AppLandingRoute() {
    IntroRoute()
}

@Composable
private fun AppSignRoute() {

}

@Composable
private fun AppMainRoute() {

}

