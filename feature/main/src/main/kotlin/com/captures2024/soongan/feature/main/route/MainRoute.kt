package com.captures2024.soongan.feature.main.route

import androidx.compose.runtime.Composable
import com.captures2024.soongan.core.designsystem.component.SoonGanBackground
import com.captures2024.soongan.feature.main.ui.MainScreen

@Composable
fun MainRoute(
    isGuestMode: Boolean,
    nickname: String,
) {
    val routeState: MainRouteState = rememberMainRouteState()

    SoonGanBackground {
        MainScreen(
            routeState = routeState,
            isGuestMode = isGuestMode,
            nickname = nickname,
        )
    }
}
