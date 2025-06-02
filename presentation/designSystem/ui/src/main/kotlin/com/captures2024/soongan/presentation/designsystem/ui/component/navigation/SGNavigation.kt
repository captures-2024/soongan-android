package com.captures2024.soongan.presentation.designsystem.ui.component.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor

@Composable
fun RowScope.SoonGanNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = when (selected) {
            true -> selectedIcon

            false -> icon
        },
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = SGNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = SGNavigationDefaults.navigationContentColor(),
            selectedTextColor = SGNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = SGNavigationDefaults.navigationContentColor(),
            indicatorColor = SGColor.transparent,
        ),
    )
}

@Composable
fun SoonGanNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = modifier.fillMaxWidth()
            .background(SGColor.primaryB)
            .clip(
                shape = RoundedCornerShape(
                    topStart = 13.dp,
                    topEnd = 13.dp,
                    bottomEnd = 0.dp,
                    bottomStart = 0.dp,
                ),
            ),
        containerColor = SGColor.white,
        contentColor = SGNavigationDefaults.navigationContentColor(),
        tonalElevation = 0.dp,
        content = content,
    )
}

object SGNavigationDefaults {
    @Composable
    fun navigationContentColor() = SGColor.primaryB

    @Composable
    fun navigationSelectedItemColor() = SGColor.primaryA
}
