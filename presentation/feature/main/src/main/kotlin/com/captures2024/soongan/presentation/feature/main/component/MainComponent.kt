package com.captures2024.soongan.presentation.feature.main.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavDestination
import com.captures2024.soongan.core.designsystem.ui.component.background.SGBackground
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.navigation.MainTopLevelDestination

@Composable
internal fun MainComponent(
    isNotViewBottomBar: Boolean,
    destinations: List<MainTopLevelDestination>,
    onNavigateToDestination: (MainTopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
            .navigationBarsPadding(),
        bottomBar = {
            if (!isNotViewBottomBar) {
                SoonGanBottomBar(
                    destinations = destinations,
                    onNavigateToDestination = onNavigateToDestination,
                    currentDestination = currentDestination,
                    modifier = Modifier.testTag("SoonGanBottomBar"),
                )
            }
        },
        content = content,
    )
}

@DevicePreviews
@Composable
private fun PreviewMainComponent() {
    SGBackground {
        MainComponent(
            isNotViewBottomBar = false,
            destinations = MainTopLevelDestination.entries,
            onNavigateToDestination = {},
            currentDestination = null,
            content = {},
        )
    }
}
