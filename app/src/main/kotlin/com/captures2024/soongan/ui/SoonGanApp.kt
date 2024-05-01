package com.captures2024.soongan.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.R
import com.captures2024.soongan.core.data.util.NetworkMonitor
import com.captures2024.soongan.core.designsystem.component.SoonGanBackground
import com.captures2024.soongan.navigation.SoonGanNavHost

@Composable
fun SoonGanApp(
    networkMonitor: NetworkMonitor,
    appState: SoonGanAppState = rememberSoonGanAppState(networkMonitor = networkMonitor),
) {
    val height = LocalConfiguration.current.screenHeightDp

    SoonGanBackground {
        val snackBarHostState = remember { SnackbarHostState() }

        val isOffline by appState.isOffline.collectAsStateWithLifecycle()

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

        SoonGanAppBody(
            appState = appState,
            hostState = snackBarHostState,
            height = height,
        )
    }
}

@Composable
fun SoonGanAppBody(
    appState: SoonGanAppState,
    hostState: SnackbarHostState,
    height: Int,
) = Scaffold(
    snackbarHost = {
        SnackbarHost(
            modifier = Modifier
                .padding(bottom = (height - 96).dp)
                .height(36.dp),
            hostState = hostState,
            snackbar = {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp),
                    shape = RoundedCornerShape(50.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFA9FE)),
                    elevation = CardDefaults.cardElevation(8.dp),
                ) {
                    Box(Modifier.fillMaxSize())
                }
            }
        )
    }
) { padding ->
    SoonGanNavHost(
        modifier = Modifier.padding(padding),
        appState = appState,
    ) { message ->
        hostState.showSnackbar(
            message = message,
            duration = SnackbarDuration.Short,
        ) == SnackbarResult.ActionPerformed
    }
}