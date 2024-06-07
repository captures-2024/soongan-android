package com.captures2024.soongan.feature.main.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconAwards
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFeed
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconHome
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconProfile

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
) {
    HOME(
        selectedIcon = MyIconPack.IconHome,
        unselectedIcon = MyIconPack.IconHome,
    ),
    FEED(
        selectedIcon = MyIconPack.IconFeed,
        unselectedIcon = MyIconPack.IconFeed,
    ),
    AWARDS(
        selectedIcon = MyIconPack.IconAwards,
        unselectedIcon = MyIconPack.IconAwards,
    ),
    PROFILE(
        selectedIcon = MyIconPack.IconProfile,
        unselectedIcon = MyIconPack.IconProfile,
    ),
}
