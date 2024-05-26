package com.captures2024.soongan.feature.main.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
) {
    HOME(
        selectedIcon = Icons.Default.Home,
        unselectedIcon = Icons.Default.Home,
    ),
    FEED(
        selectedIcon = Icons.Default.Home,
        unselectedIcon = Icons.Default.Home,
    ),
    AWARDS(
        selectedIcon = Icons.Default.Home,
        unselectedIcon = Icons.Default.Home,
    ),
    PROFILE(
        selectedIcon = Icons.Default.Home,
        unselectedIcon = Icons.Default.Home,
    ),
}
