package com.captures2024.soongan.feature.main.ui

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.feature.main.navigation.MainRouteNavHost
import com.captures2024.soongan.feature.main.route.MainRouteState

@Composable
internal fun MainScreen(
    isGuestMode: Boolean,
    routeState: MainRouteState,
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
    MainRouteNavHost(
        modifier = Modifier.padding(padding),
        isGuestMode = isGuestMode,
        routeState = routeState,
    ) { message ->
        hostState.showSnackbar(
            message = message,
            duration = SnackbarDuration.Short,
        ) == SnackbarResult.ActionPerformed
    }
}