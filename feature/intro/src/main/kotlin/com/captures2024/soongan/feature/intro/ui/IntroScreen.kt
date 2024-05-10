package com.captures2024.soongan.feature.intro.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.captures2024.soongan.feature.intro.navigation.IntroNavHost

@Composable
internal fun IntroScreen(routeState: IntroRouteState) = Scaffold { padding ->
    IntroNavHost(
        modifier = Modifier.padding(padding),
        routeState = routeState,
    )
}