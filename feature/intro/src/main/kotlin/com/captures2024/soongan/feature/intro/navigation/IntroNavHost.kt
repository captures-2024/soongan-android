package com.captures2024.soongan.feature.intro.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.captures2024.soongan.feature.intro.route.IntroRouteState

@Composable
internal fun IntroNavHost(
    modifier: Modifier = Modifier,
    routeState: IntroRouteState,
) {
    val navController = routeState.navController

}