package com.captures2024.soongan.presentation.feature.main.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.captures2024.soongan.presentation.designsystem.ui.component.navigation.SoonGanNavigationBar
import com.captures2024.soongan.presentation.designsystem.ui.component.navigation.SoonGanNavigationBarItem
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.feature.main.navigation.MainTopLevelDestination
import kotlin.collections.forEach

@Composable
internal fun SoonGanBottomBar(
    isNotViewBottomBar: Boolean,
    destinations: List<MainTopLevelDestination>,
    onNavigateToDestination: (MainTopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
    isNotReadNotification: Boolean = false,
) {
    SoonGanNavigationBar(
        modifier = modifier,
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)

            SoonGanNavigationBarItem(
                selected = selected,
                onClick = {
                    if (!isNotViewBottomBar && !selected) {
                        onNavigateToDestination(destination)
                    }
                },
                icon = {
                    when (destination) {
                        MainTopLevelDestination.PROFILE -> {
                            Box {
                                if (isNotReadNotification) {
                                    Box(
                                        modifier = Modifier.wrapContentSize(),
                                    ) {
                                        Canvas(
                                            modifier = Modifier
                                                .size(8.dp)
                                                .align(Alignment.Center),
                                        ) {
                                            drawCircle(
                                                color = SGColor.Main.primary,
                                                radius = size.width / 2,
                                            )
                                        }
                                    }
                                }

                                Icon(
                                    imageVector = destination.unselectedIcon,
                                    contentDescription = null,
                                    tint = SGColor.primaryA.copy(alpha = 0.3f),
                                    modifier = Modifier.size(24.dp, 24.dp),
                                )
                            }
                        }

                        else -> Icon(
                            imageVector = destination.unselectedIcon,
                            contentDescription = null,
                            tint = SGColor.primaryA.copy(alpha = 0.3f),
                            modifier = Modifier.size(
                                width = 24.dp,
                                height = 24.dp,
                            ),
                        )
                    }
                },
                selectedIcon = {
                    when (destination) {
                        MainTopLevelDestination.PROFILE -> {
                            Box {
                                if (isNotReadNotification) {
                                    Box(
                                        modifier = Modifier.wrapContentSize(),
                                    ) {
                                        Canvas(
                                            modifier = Modifier
                                                .size(8.dp)
                                                .align(Alignment.Center),
                                        ) {
                                            drawCircle(
                                                color = SGColor.Main.primary,
                                                radius = size.width / 2,
                                            )
                                        }
                                    }
                                }

                                Icon(
                                    imageVector = destination.selectedIcon,
                                    contentDescription = null,
                                    tint = SGColor.primaryA,
                                    modifier = Modifier.size(
                                        width = 24.dp,
                                        height = 24.dp,
                                    ),
                                )
                            }
                        }

                        else -> Icon(
                            imageVector = destination.selectedIcon,
                            contentDescription = null,
                            tint = SGColor.primaryA,
                            modifier = Modifier.size(
                                width = 24.dp,
                                height = 24.dp,
                            ),
                        )
                    }
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
