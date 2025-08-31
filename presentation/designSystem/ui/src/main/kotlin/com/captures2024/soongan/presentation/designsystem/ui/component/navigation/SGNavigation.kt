package com.captures2024.soongan.presentation.designsystem.ui.component.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
    isNotViewBottomBar: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .background(SGColor.Grayscale.white)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = {},
            ),
    ) {
        if (isNotViewBottomBar.not()) {
            content()
        }
    }
}

object SGNavigationDefaults {
    @Composable
    fun navigationContentColor() = SGColor.Grayscale.black60

    @Composable
    fun navigationSelectedItemColor() = SGColor.Grayscale.black100
}
