package com.captures2024.soongan.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.captures2024.soongan.core.designsystem.theme.SoonGanTheme
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.state.AppRootRouteState
import com.captures2024.soongan.state.AppRootUIState

@Composable
internal fun AppRootScreen(
    uiState: AppRootUIState,
    appLandingRoute: @Composable () -> Unit,
    appSignRoute: @Composable () -> Unit,
    appMainRoute: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center,
    ) {
        when (uiState.rootRouteState) {
            AppRootRouteState.LANDING -> appLandingRoute()

            AppRootRouteState.SIGN -> appSignRoute()

            AppRootRouteState.MAIN -> appMainRoute()
        }
    }
}

@DevicePreviews
@Composable
private fun PreviewAppRootScreen() {
    SoonGanTheme {
        AppRootScreen(
            uiState = AppRootUIState(),
            appLandingRoute = {},
            appSignRoute = {},
            appMainRoute = {},
        )
    }
}