package com.captures2024.soongan.feature.feed.utils

import androidx.compose.ui.graphics.vector.ImageVector
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFilterLike
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFilterNew
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFilterOld
import com.captures2024.soongan.core.viewmodel.model.PostOrderType
import com.captures2024.soongan.feature.feed.R

internal fun PostOrderType.getTextId(): Int = when (this) {
    PostOrderType.MOST_LIKED -> R.string.filter_likes

    PostOrderType.OLDEST -> R.string.filter_oldest

    PostOrderType.LATEST -> R.string.filter_newest
}

internal fun PostOrderType.getIcon(): ImageVector = when (this) {
    PostOrderType.MOST_LIKED -> MyIconPack.IconFilterLike

    PostOrderType.OLDEST -> MyIconPack.IconFilterOld

    PostOrderType.LATEST -> MyIconPack.IconFilterNew
}
