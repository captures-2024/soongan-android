package com.captures2024.soongan.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.core.designsystem.component.SoonGanBackground
import com.captures2024.soongan.core.viewmodel.AppRootViewModel
import com.captures2024.soongan.core.viewmodel.SignInViewModel
import com.captures2024.soongan.feature.intro.route.IntroRoute
import com.captures2024.soongan.feature.main.route.MainRoute
import com.captures2024.soongan.feature.sign.route.SignRoute
import com.captures2024.soongan.ui.AppRootScreen

@Composable
internal fun AppRoute(
    appRootViewModel: AppRootViewModel,
    signInViewModel: SignInViewModel,
) {
    val uiState by appRootViewModel.state.collectAsStateWithLifecycle()

    SoonGanBackground {
        AppRootScreen(
            uiState = uiState,
            appLandingRoute = @Composable { AppLandingRoute() },
            appSignRoute = @Composable {
                AppSignRoute(
                    signInViewModel = signInViewModel,
                )
            },
            appMainRoute = @Composable {
                AppMainRoute(
                    isGuestMode = uiState.isGuestMode(),
                )
            },
        )
    }
}


@Composable
private fun AppLandingRoute() {
    IntroRoute()
}

@Composable
private fun AppSignRoute(
    signInViewModel: SignInViewModel,
) {
    SignRoute(signInViewModel = signInViewModel)
}

@Composable
private fun AppMainRoute(isGuestMode: Boolean) {
    MainRoute(isGuestMode = isGuestMode)
}

