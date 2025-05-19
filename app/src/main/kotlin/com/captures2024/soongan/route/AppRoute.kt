package com.captures2024.soongan.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.core.designsystem.ui.component.background.SGBackground
import com.captures2024.soongan.core.viewmodel.AppRootViewModel
import com.captures2024.soongan.feature.intro.route.IntroRoute
import com.captures2024.soongan.feature.main.route.MainRoute
import com.captures2024.soongan.feature.main.route.MainRouteState
import com.captures2024.soongan.feature.main.route.rememberMainRouteState
import com.captures2024.soongan.presentation.feature.sign.route.SignRoute
import com.captures2024.soongan.ui.AppRootScreen

@Composable
internal fun AppRoute(appRootViewModel: AppRootViewModel) {
    val uiState by appRootViewModel.state.collectAsStateWithLifecycle()
    val routeState = rememberMainRouteState(isGuestMode = uiState.isGuestMode)

    SGBackground {
        AppRootScreen(
            intent = appRootViewModel::intent,
            uiState = uiState,
            appLandingRoute = @Composable {
                AppLandingRoute()
            },
            appSignRoute = @Composable {
                AppSignRoute(routeState = routeState)
            },
            appMainRoute = @Composable {
                AppMainRoute(routeState = routeState)
            },
        )

        DialogHost(appRootViewModel)
        LoadingHost(visible = uiState.isLoading)
    }
}

@Composable
private fun AppLandingRoute() {
    IntroRoute()
}

@Composable
private fun AppSignRoute(routeState: MainRouteState) {
    SignRoute(navController = routeState.navController)
}

@Composable
private fun AppMainRoute(routeState: MainRouteState) {
    MainRoute(routeState)
}

// import androidx.compose.runtime.Composable
// import androidx.compose.runtime.getValue
// import androidx.lifecycle.compose.collectAsStateWithLifecycle
// import androidx.navigation.NavHostController
// import androidx.navigation.compose.rememberNavController
// import com.captures2024.soongan.core.designsystem.ui.component.background.SGBackground
// import com.captures2024.soongan.core.viewmodel.AppRootViewModel
// import com.captures2024.soongan.feature.intro.route.IntroRoute
// import com.captures2024.soongan.presentation.feature.main.route.MainRoute
// import com.captures2024.soongan.presentation.feature.sign.route.SignRoute
// import com.captures2024.soongan.ui.AppRootScreen
//
// @Composable
// internal fun AppRoute(appRootViewModel: AppRootViewModel) {
//     val uiState by appRootViewModel.state.collectAsStateWithLifecycle()
//
//     val navController = rememberNavController()
//
//     SGBackground {
//         AppRootScreen(
//             intent = appRootViewModel::intent,
//             uiState = uiState,
//             appLandingRoute = @Composable {
//                 AppLandingRoute()
//             },
//             appSignRoute = @Composable {
//                 AppSignRoute(
//                     navController = navController,
//                 )
//             },
//             appMainRoute = @Composable {
//                 AppMainRoute(
//                     isGuestMode = uiState.isGuestMode,
//                     navController = navController,
//                 )
//             },
//         )
//
//         DialogHost(appRootViewModel)
//         LoadingHost(visible = uiState.isLoading)
//     }
// }
//
// @Composable
// private fun AppLandingRoute() {
//     IntroRoute()
// }
//
// @Composable
// private fun AppSignRoute(navController: NavHostController) {
//     SignRoute(
//         navController = navController,
//     )
// }
//
// @Composable
// private fun AppMainRoute(
//     isGuestMode: Boolean,
//     navController: NavHostController,
// ) {
//     MainRoute(
//         isGuestMode = isGuestMode,
//         navController = navController,
//     )
// }
