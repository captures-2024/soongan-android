package com.captures2024.soongan.presentation.feature.main.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.captures2024.soongan.core.designsystem.ui.component.navigation.SoonGanNavigationBar
import com.captures2024.soongan.core.designsystem.ui.component.navigation.SoonGanNavigationBarItem
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.feature.main.navigation.MainTopLevelDestination
import kotlin.collections.forEach

@Composable
internal fun SoonGanBottomBar(
    destinations: List<MainTopLevelDestination>,
    onNavigateToDestination: (MainTopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    SoonGanNavigationBar(
        modifier = modifier,
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)

            SoonGanNavigationBarItem(
                selected = selected,
                onClick = { if (!selected) { onNavigateToDestination(destination) } },
                icon = {
                    Icon(
                        imageVector = destination.unselectedIcon,
                        contentDescription = null,
                        tint = SGColor.primaryA.copy(alpha = 0.3f),
                        modifier = Modifier.size(24.dp, 24.dp),
                    )
                },
                selectedIcon = {
                    Icon(
                        imageVector = destination.selectedIcon,
                        contentDescription = null,
                        tint = SGColor.primaryA,
                        modifier = Modifier.size(24.dp, 24.dp),
                    )
                },
            )
        }
    }
}

internal fun NavDestination?.isTopLevelDestinationInHierarchy(destination: MainTopLevelDestination): Boolean {
    return this?.hierarchy
        ?.any {
            it.route
                ?.split(".")
                ?.lastOrNull()
                ?.removeSuffix("Navigator")
                ?.equals(destination.name, true) == true
        } == true
}
