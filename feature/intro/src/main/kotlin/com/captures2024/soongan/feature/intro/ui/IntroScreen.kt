package com.captures2024.soongan.feature.intro.ui

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.captures2024.soongan.feature.intro.navigation.IntroNavHost
import com.captures2024.soongan.feature.intro.route.IntroRouteState

@Composable
internal fun IntroScreen(routeState: IntroRouteState) {
    Log.d("IntroScreen", "View IntroScreen")

    Scaffold { padding ->
        IntroNavHost(
            modifier = Modifier.padding(padding),
            routeState = routeState,
        )
    }
}