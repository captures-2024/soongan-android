package com.captures2024.soongan.feature.main.route

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.core.analytics.NetworkMonitor
import com.captures2024.soongan.core.design.R
import com.captures2024.soongan.core.designsystem.component.SoonGanBackground
import com.captures2024.soongan.feature.main.ui.MainScreen

@Composable
internal fun MainRoute(
    isGuestMode: Boolean,
    networkMonitor: NetworkMonitor,
    routeState: MainRouteState = rememberMainRouteState(networkMonitor = networkMonitor),
) {
    val height = LocalConfiguration.current.screenHeightDp

    SoonGanBackground {
        val snackBarHostState = remember { SnackbarHostState() }

        val isOffline by routeState.isOffline.collectAsStateWithLifecycle()

        // If user is not connected to the internet show a snack bar to inform them.
        val notConnectedMessage = stringResource(R.string.not_connected)
        LaunchedEffect(isOffline) {
            if (isOffline) {
                snackBarHostState.showSnackbar(
                    message = notConnectedMessage,
                    duration = SnackbarDuration.Indefinite,
                )
            }
        }

        MainScreen(
            routeState = routeState,
            isGuestMode = isGuestMode,
            hostState = snackBarHostState,
            height = height
        )
    }
}
