package com.captures2024.soongan.feature.main.ui

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.captures2024.soongan.core.designsystem.component.SoonGanNavigationBar
import com.captures2024.soongan.core.designsystem.component.SoonGanNavigationBarItem
import com.captures2024.soongan.feature.main.navigation.TopLevelDestination
import timber.log.Timber

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
                    )
                },
                selectedIcon = {
                    Icon(
                        imageVector = destination.selectedIcon,
                        contentDescription = null,
                    )
                },
            )
        }
    }
}

internal fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy
        ?.any {
            it.route?.contains(destination.name, true) ?: false
        } ?: false
