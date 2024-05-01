package com.captures2024.soongan.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.captures2024.soongan.ui.SoonGanAppState

@Composable
fun SoonGanNavHost(
    modifier: Modifier = Modifier,
    appState: SoonGanAppState,
    onShowSnackBar: suspend (String) -> Boolean,
) {
    val navController = appState.navController

//    NavHost(
//        modifier = modifier,
//        navController = navController,
//        startDestination = ,
//        enterTransition = { EnterTransition.None },
//        exitTransition = { ExitTransition.None },
//        popEnterTransition = { EnterTransition.None },
//        popExitTransition = { ExitTransition.None }
//    ) {
//
//    }

}