package com.captures2024.soongan.presentation.feature.main.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import com.captures2024.soongan.presentation.feature.main.navigation.MainNavigationState
import com.captures2024.soongan.presentation.feature.main.navigation.MainTopLevelDestination

@Composable
internal fun MainComponent(
    navigationState: MainNavigationState,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        bottomBar = {
            val isNotViewBottomBar = isNotViewBottomBar(
                currentDestination = navigationState.currentDestination,
                topLevelDestinations = navigationState.topLevelDestinations,
            )

            SoonGanBottomBar(
                isNotViewBottomBar = isNotViewBottomBar,
                destinations = navigationState.topLevelDestinations,
                onNavigateToDestination = navigationState::navigateToTopLevelDestination,
                currentDestination = navigationState.currentDestination,
                modifier = Modifier
                    .let {
                        return@let when (isNotViewBottomBar) {
                            true -> it.height(0.dp)
                            false -> it.wrapContentHeight()
                        }
                    }
                    .animateContentSize()
                    .testTag("SoonGanBottomBar"),
            )
        },
        content = content,
    )
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
