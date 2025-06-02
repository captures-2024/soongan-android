package com.captures2024.soongan.presentation.feature.main.home.utils.extension

import androidx.compose.ui.graphics.vector.ImageVector
import com.captures2024.soongan.presentation.designsystem.icon.MyIconPack
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.IconFilterLike
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.IconFilterNew
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.IconFilterOld
import com.captures2024.soongan.presentation.feature.main.home.R
import com.captures2024.soongan.presentation.viewmodel.model.PostOrderType

internal fun PostOrderType.getStringResId(): Int = when (this) {
    PostOrderType.MOST_LIKED -> R.string.filter_likes

    PostOrderType.OLDEST -> R.string.filter_oldest

    PostOrderType.LATEST -> R.string.filter_newest
}

internal fun PostOrderType.getIcon(): ImageVector = when (this) {
    PostOrderType.MOST_LIKED -> MyIconPack.IconFilterLike

    PostOrderType.OLDEST -> MyIconPack.IconFilterOld

    PostOrderType.LATEST -> MyIconPack.IconFilterNew
}
