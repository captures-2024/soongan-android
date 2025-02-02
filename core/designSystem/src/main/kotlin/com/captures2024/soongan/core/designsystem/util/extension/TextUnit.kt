package com.captures2024.soongan.core.designsystem.util.extension

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

/* figma에서 letterSpacing이 % 값인 경우, em으로 받아 처리 */
internal fun normalizeLetterSpacing(
    fontSize: TextUnit,
    letterSpacing: TextUnit,
): TextUnit = when (letterSpacing) {
    TextUnit.Unspecified -> letterSpacing

    else -> when (letterSpacing.isEm) {
        true -> fontSize.value.sp * (letterSpacing.value / 100)

        false -> letterSpacing
    }
}
