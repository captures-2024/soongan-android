package com.captures2024.soongan.presentation.feature.main.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.captures2024.soongan.presentation.designsystem.icon.MyIconPack
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.IconNonSelectedAwards
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.IconNonSelectedFeed
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.IconNonSelectedHome
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.IconNonSelectedProfile
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.IconSelectedAwards
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.IconSelectedFeed
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.IconSelectedHome
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.IconSelectedProfile

internal enum class MainTopLevelDestination(
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
