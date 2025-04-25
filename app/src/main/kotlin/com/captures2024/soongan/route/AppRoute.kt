package com.captures2024.soongan.route

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import com.captures2024.soongan.core.designsystem.ui.component.background.SGBackground
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.viewmodel.AppRootViewModel
import com.captures2024.soongan.feature.intro.route.IntroRoute
import com.captures2024.soongan.feature.main.route.MainRoute
import com.captures2024.soongan.feature.main.route.MainRouteState
import com.captures2024.soongan.feature.main.route.rememberMainRouteState
import com.captures2024.soongan.feature.sign.route.SignRoute
import com.captures2024.soongan.ui.AppRootScreen
import kotlinx.coroutines.delay

@Composable
internal fun AppRoute(
    appRootViewModel: AppRootViewModel,
) {
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
                AppSignRoute()
            },
            appMainRoute = @Composable {
                AppMainRoute(routeState = routeState)
            },
        )

//        NotificationHost(navController = routeState.navController)
        AppLoading(visible = uiState.isLoading)
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
                .fillMaxSize()
                .background(SGColor.transparent)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = {},
                ),
            contentAlignment = Alignment.Center,
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
private fun AppSignRoute() {
    SignRoute()
}

@Composable
private fun AppMainRoute(routeState: MainRouteState) {
    MainRoute(routeState)
}

@Composable
private fun NotificationHost(
//    navController: NavController,
//    notificationViewModel: NotificationViewModel = hiltViewModel(),
) {
//    val state by notificationViewModel.state.collectAsState()

    // TODO
}
