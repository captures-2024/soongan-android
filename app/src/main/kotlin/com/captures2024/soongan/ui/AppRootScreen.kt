package com.captures2024.soongan.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.captures2024.soongan.core.designsystem.theme.SoonGanTheme
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.viewmodel.state.AppRootUIState
import com.captures2024.soongan.core.viewmodel.utils.AppRootRoute

@Composable
internal fun AppRootScreen(
    uiState: AppRootUIState,
    appLandingRoute: @Composable () -> Unit,
    appSignRoute: @Composable () -> Unit,
    appMainRoute: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        when (uiState.rootRouteState) {
            AppRootRoute.LANDING -> appLandingRoute()

            AppRootRoute.SIGN -> appSignRoute()

            AppRootRoute.MAIN -> appMainRoute()
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