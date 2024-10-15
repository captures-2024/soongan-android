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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import com.captures2024.soongan.feature.main.navigation.MainRouteNavHost
import com.captures2024.soongan.feature.main.navigation.TopLevelDestination
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
    },
    bottomBar = {
        val isNotViewBottomBar = isNotViewBottomBar(
            currentDestination = routeState.currentDestination,
            topLevelDestinations = routeState.topLevelDestinations
        )

        if (!isNotViewBottomBar) {
            SoonGanBottomBar(
                destinations = routeState.topLevelDestinations,
                onNavigateToDestination = routeState::navigateToTopLevelDestination,
                currentDestination = routeState.currentDestination,
                modifier = Modifier.testTag("SoonGanBottomBar"),
            )
        }
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

/**
 * 바텀 네비게이션 바를 표시할지 안할지 결정하는 함수
 * @return topLevel에 해당한다면 즉, home, feed, awards, profile에 해당하면 false 해당하지 않으면 true
 * @param currentDestination 현재 Destination,
 * @param topLevelDestinations Top Level에 해당하는 Destination 리스트
 * **/
private fun isNotViewBottomBar(
    currentDestination: NavDestination?,
    topLevelDestinations: List<TopLevelDestination>
): Boolean {
    var isNotViewBottomBar = true
//    Timber.tag("isNotViewBottomBar").d("currentDestination = $currentDestination")
    for (topLevelDestination in topLevelDestinations) {
        if (currentDestination.isTopLevelDestinationInHierarchy(topLevelDestination)) {
            isNotViewBottomBar = false
            break
        }
    }

    return isNotViewBottomBar
}