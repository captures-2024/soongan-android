package com.captures2024.soongan.core.designsystem.icon.myiconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.designsystem.icon.MyIconPack

public val MyIconPack.IconNonFillRightArrow: ImageVector
    get() {
        if (_iconNonFillRightArrow != null) {
            return _iconNonFillRightArrow!!
        }
        _iconNonFillRightArrow = Builder(
            name = "IconNonFillRightArrow",
            defaultWidth = 20.0.dp,
            defaultHeight = 16.0.dp,
            viewportWidth = 20.0f,
            viewportHeight = 16.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFF252525)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(12.3809f, 16.0f)
                lineTo(11.0238f, 14.6f)
                lineTo(16.3571f, 9.0f)
                lineTo(-0.0f, 9.0f)
                lineTo(-0.0f, 7.0f)
                lineTo(16.3571f, 7.0f)
                lineTo(11.0238f, 1.4f)
                lineTo(12.3809f, 0.0f)
                lineTo(20.0f, 8.0f)
                lineTo(12.3809f, 16.0f)
                close()
            }
        }.build()

        return _iconNonFillRightArrow!!
    }

private var _iconNonFillRightArrow: ImageVector? = null
