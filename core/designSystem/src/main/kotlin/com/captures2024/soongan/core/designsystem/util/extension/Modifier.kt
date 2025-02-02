package com.captures2024.soongan.core.designsystem.util.extension

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import com.captures2024.soongan.core.designsystem.theme.SGDimension

fun Modifier.sgBottomBarPadding() = this.padding(
    bottom = SGDimension.bottomBarHeight,
)
