package com.captures2024.soongan.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.captures2024.soongan.core.designsystem.theme.SGTheme
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.viewmodel.AppRootViewModel
import com.captures2024.soongan.core.viewmodel.model.AppRootRoute

@Composable
internal fun AppRootScreen(
    uiState: AppRootViewModel.State,
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
    SGTheme {
        AppRootScreen(
            uiState = AppRootViewModel.State(),
            appLandingRoute = {},
            appSignRoute = {},
            appMainRoute = {},
        )
    }
}