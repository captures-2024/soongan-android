package com.captures2024.soongan.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.core.designsystem.component.SoonGanBackground
import com.captures2024.soongan.core.viewmodel.AppRootViewModel
import com.captures2024.soongan.core.viewmodel.sign.SignViewModel
import com.captures2024.soongan.feature.intro.route.IntroRoute
import com.captures2024.soongan.feature.main.route.MainRoute
import com.captures2024.soongan.feature.sign.route.SignRoute
import com.captures2024.soongan.ui.AppRootScreen

@Composable
internal fun AppRoute(
    appRootViewModel: AppRootViewModel,
    signViewModel: SignViewModel,
) {
    val uiState by appRootViewModel.state.collectAsStateWithLifecycle()

    SoonGanBackground {
        AppRootScreen(
            uiState = uiState,
            appLandingRoute = @Composable { AppLandingRoute() },
            appSignRoute = @Composable {
                AppSignRoute(
                    signViewModel = signViewModel,
                )
            },
            appMainRoute = @Composable {
                AppMainRoute(
                    isGuestMode = uiState.isGuestMode(),
                    nickname = uiState.getNickname()
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
    signViewModel: SignViewModel,
) {
    SignRoute(signViewModel = signViewModel)
}

@Composable
private fun AppMainRoute(
    isGuestMode: Boolean,
    nickname: String,
) {
    MainRoute(
        isGuestMode = isGuestMode,
        nickname = nickname
    )
}

