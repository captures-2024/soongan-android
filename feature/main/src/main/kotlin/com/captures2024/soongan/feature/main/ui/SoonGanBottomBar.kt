package com.captures2024.soongan.feature.main.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.captures2024.soongan.core.designsystem.component.SoonGanNavigationBar
import com.captures2024.soongan.core.designsystem.component.SoonGanNavigationBarItem
import com.captures2024.soongan.core.designsystem.theme.PrimaryA
import com.captures2024.soongan.feature.main.navigation.TopLevelDestination

@Composable
internal fun SoonGanBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
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
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    Icon(
                        imageVector = destination.unselectedIcon,
                        contentDescription = null,
                        tint = PrimaryA.copy(alpha = 0.3f),
                        modifier = Modifier.size(24.dp, 24.dp)
                    )
                },
                selectedIcon = {
                    Icon(
                        imageVector = destination.selectedIcon,
                        contentDescription = null,
                        tint = PrimaryA,
                        modifier = Modifier.size(24.dp, 24.dp)
                    )
                },
            )
        }
    }
}

internal fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination): Boolean {
    return this?.hierarchy
        ?.any {
            it.route
                ?.split(".")
                ?.lastOrNull()
                ?.removeSuffix("Navigator")
                ?.equals(destination.name, true)
                ?: false
        } ?: false
}

