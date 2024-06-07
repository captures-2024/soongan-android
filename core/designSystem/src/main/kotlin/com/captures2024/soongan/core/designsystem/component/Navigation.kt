package com.captures2024.soongan.core.designsystem.component

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.designsystem.theme.PrimaryA
import com.captures2024.soongan.core.designsystem.theme.PrimaryB

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
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = SoonGanNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = SoonGanNavigationDefaults.navigationContentColor(),
            selectedTextColor = SoonGanNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = SoonGanNavigationDefaults.navigationContentColor(),
            indicatorColor = Color.Transparent,
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
            .background(PrimaryB)
            .clip(
                shape = RoundedCornerShape(
                    topStart = 13.dp,
                    topEnd = 13.dp,
                    bottomEnd = 0.dp,
                    bottomStart = 0.dp
                )
            ),
        containerColor = Color.White,
        contentColor = SoonGanNavigationDefaults.navigationContentColor(),
        tonalElevation = 0.dp,
        content = content,
    )
}

object SoonGanNavigationDefaults {
    @Composable
    fun navigationContentColor() = PrimaryB

    @Composable
    fun navigationSelectedItemColor() = PrimaryA
}
