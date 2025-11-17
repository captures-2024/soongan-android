package com.captures2024.soongan.presentation.feature.root.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.captures2024.soongan.presentation.designsystem.ui.component.background.SGBackground
import com.captures2024.soongan.presentation.feature.root.component.screen.AppRootScreen
import com.captures2024.soongan.presentation.viewmodel.AppViewModel

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
            },
        )

        if (state.isInitialized) {
            NotificationHost(navController)
        }

        DialogHost(appViewModel = viewModel)
        LoadingHost(visible = state.isLoading)
    }
}
