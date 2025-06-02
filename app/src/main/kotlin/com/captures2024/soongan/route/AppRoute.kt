package com.captures2024.soongan.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.captures2024.soongan.core.designsystem.ui.component.background.SGBackground
import com.captures2024.soongan.presentation.feature.main.route.MainRoute
import com.captures2024.soongan.presentation.feature.sign.route.SignRoute
import com.captures2024.soongan.presentation.viewmodel.AppViewModel
import com.captures2024.soongan.ui.AppRootScreen

@Composable
internal fun AppRoute(viewModel: AppViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val navController = rememberNavController()

    SGBackground {
        AppRootScreen(
            intent = viewModel::intent,
            state = state,
            appLandingRoute = @Composable {
                AppLandingRoute()
            },
            appSignRoute = @Composable {
                AppSignRoute(
                    navController = navController,
                )
            },
            appMainRoute = @Composable {
                AppMainRoute(
                    isGuestMode = state.isGuestMode,
                    navController = navController,
                )
            }
        )

        DialogHost(viewModel = viewModel)
        LoadingHost(visible = state.isLoading)
    }
}

@Composable
private fun AppLandingRoute() {

}

@Composable
private fun AppSignRoute(navController: NavHostController) {
    SignRoute(
        navController = navController,
    )
}

@Composable
private fun AppMainRoute(
    isGuestMode: Boolean,
    navController: NavHostController,
) {
    MainRoute(
        isGuestMode = isGuestMode,
        navController = navController,
    )
}
