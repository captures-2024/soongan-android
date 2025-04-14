package com.captures2024.soongan.feature.main.route

import androidx.compose.runtime.Composable
import com.captures2024.soongan.core.designsystem.ui.component.background.SGBackground
import com.captures2024.soongan.feature.main.ui.MainScreen

@Composable
fun MainRoute(routeState: MainRouteState) {
    SGBackground {
        MainScreen(routeState = routeState)
    }
}
