package com.captures2024.soongan.feature.home.utils

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFilterLike
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFilterNew
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFilterOld
import com.captures2024.soongan.feature.home.R as RHome

internal enum class PostOrderType(
    @StringRes val textId: Int,
    val icon: ImageVector,
) {
    MOST_LIKED(textId = RHome.string.filter_likes, icon = MyIconPack.IconFilterLike),
    OLDEST(textId = RHome.string.filter_oldest, icon = MyIconPack.IconFilterOld),
    LATEST(textId = RHome.string.filter_newest, icon = MyIconPack.IconFilterNew),
}
