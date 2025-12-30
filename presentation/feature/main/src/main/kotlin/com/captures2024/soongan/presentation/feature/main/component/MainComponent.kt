package com.captures2024.soongan.presentation.feature.main.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavDestination
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.feature.main.navigation.MainNavigationState
import com.captures2024.soongan.presentation.feature.main.navigation.MainTopLevelDestination

@Composable
internal fun MainComponent(
    navigationState: MainNavigationState,
    isNotReadNotification: Boolean,
    content: @Composable () -> Unit,
) {
    val isNotViewBottomBar = isNotViewBottomBar(
        currentDestination = navigationState.currentDestination,
        topLevelDestinations = navigationState.topLevelDestinations,
    )

    Box(
        modifier = Modifier.fillMaxSize()
            .background(SGColor.BG.background)
            .navigationBarsPadding(),
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .statusBarsPadding(),
        ) {
            content()
        }

        SoonGanBottomBar(
            isNotViewBottomBar = isNotViewBottomBar,
            destinations = navigationState.topLevelDestinations,
            onNavigateToDestination = navigationState::navigateToTopLevelDestination,
            currentDestination = navigationState.currentDestination,
            modifier = Modifier.wrapContentHeight()
                .animateContentSize()
                .align(Alignment.BottomCenter)
                .testTag("SoonGanBottomBar"),
            isNotReadNotification = isNotReadNotification,
        )
    }
}

/**
 * 바텀 네비게이션 바를 표시할지 안할지 결정하는 함수
 * @return topLevel에 해당한다면 즉, home, feed, awards, profile에 해당하면 false 해당하지 않으면 true
 * @param currentDestination 현재 Destination,
 * @param topLevelDestinations Top Level에 해당하는 Destination 리스트
 * **/
@Composable
private fun isNotViewBottomBar(
    currentDestination: NavDestination?,
    topLevelDestinations: List<MainTopLevelDestination>,
): Boolean {
    for (topLevelDestination in topLevelDestinations) {
        if (currentDestination.isTopLevelDestinationInHierarchy(topLevelDestination)) {
            return false
        }
    }

    return true
}
