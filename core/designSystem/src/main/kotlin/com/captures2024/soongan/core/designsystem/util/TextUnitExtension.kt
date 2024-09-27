package com.captures2024.soongan.core.designsystem.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp


internal val TextUnit.nonScaleSp
    @Composable
    get() = when (this) {
        TextUnit.Unspecified -> this
        else -> (this.value / LocalDensity.current.fontScale).sp
    }
