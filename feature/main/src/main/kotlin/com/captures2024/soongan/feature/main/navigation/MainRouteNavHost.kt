package com.captures2024.soongan.feature.main.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.captures2024.soongan.feature.main.route.MainRouteState

@Composable
internal fun MainRouteNavHost(
    modifier: Modifier = Modifier,
    routeState: MainRouteState,
) {
    val navController = routeState.navController

//    NavHost(
//        modifier = modifier,
//        navController = navController,
//        startDestination = HOME_NAVIGATION_ROUTE,
//        enterTransition = { EnterTransition.None },
//        exitTransition = { ExitTransition.None },
//        popEnterTransition = { EnterTransition.None },
//        popExitTransition = { ExitTransition.None }
//    ) {
//
//    }
}

