package com.captures2024.soongan.feature.main.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonSelectedAwards
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonSelectedFeed
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonSelectedHome
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonSelectedProfile
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconSelectedAwards
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconSelectedFeed
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconSelectedHome
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconSelectedProfile

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
) {
    HOME(
        selectedIcon = MyIconPack.IconSelectedHome,
        unselectedIcon = MyIconPack.IconNonSelectedHome,
    ),
    FEED(
        selectedIcon = MyIconPack.IconSelectedFeed,
        unselectedIcon = MyIconPack.IconNonSelectedFeed,
    ),
    AWARDS(
        selectedIcon = MyIconPack.IconSelectedAwards,
        unselectedIcon = MyIconPack.IconNonSelectedAwards,
    ),
    PROFILE(
        selectedIcon = MyIconPack.IconSelectedProfile,
        unselectedIcon = MyIconPack.IconNonSelectedProfile,
    ),
}
