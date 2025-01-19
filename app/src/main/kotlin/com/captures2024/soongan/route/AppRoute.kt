package com.captures2024.soongan.route

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.core.designsystem.component.SoonGanBackground
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.viewmodel.AppRootViewModel
import com.captures2024.soongan.core.viewmodel.sign.SignViewModel
import com.captures2024.soongan.feature.intro.route.IntroRoute
import com.captures2024.soongan.feature.main.route.MainRoute
import com.captures2024.soongan.feature.sign.route.SignRoute
import com.captures2024.soongan.ui.AppRootScreen
import kotlinx.coroutines.delay

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
                    isGuestMode = uiState.isGuestMode,
                    nickname = uiState.currentMember?.nickname ?: "Guest"
                )
            },
        )

        AppLoading(uiState.isLoading)
    }
}

@Composable
private fun AppLoading(visible: Pair<Boolean, Long>) {
    val animationVisible = remember(visible) { mutableStateOf(visible.first) }
    LaunchedEffect(visible) {
        if (visible.first) {
            delay(20000)
            animationVisible.value = false
        }
    }

    AnimatedVisibility(
        visible = animationVisible.value,
        modifier = Modifier.fillMaxSize(),
        enter = fadeIn(animationSpec = tween(delayMillis = 100)),
        exit = fadeOut(animationSpec = tween(delayMillis = 100)),
    ) {
        Box(
            Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = SGColor.primaryA,
                modifier = Modifier,
                strokeWidth = 4.dp,
                trackColor = SGColor.transparent,
                strokeCap = StrokeCap.Round,
            )
        }
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

