package com.captures2024.soongan.presentation.designsystem.icon.myiconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.presentation.designsystem.icon.MyIconPack

public val MyIconPack.IconArrowRightFromBracket: ImageVector
    get() {
        if (_iconArrowRightFromBracket != null) {
            return _iconArrowRightFromBracket!!
        }
        _iconArrowRightFromBracket = Builder(
            name = "IconArrowRightFromBracket",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFC0000)),
                stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(2.6667f, 24.0f)
                curveTo(1.9333f, 24.0f, 1.3053f, 23.7391f, 0.7827f, 23.2173f)
                curveTo(0.2609f, 22.6947f, 0.0f, 22.0667f, 0.0f, 21.3333f)
                verticalLineTo(2.6667f)
                curveTo(0.0f, 1.9333f, 0.2609f, 1.3053f, 0.7827f, 0.7827f)
                curveTo(1.3053f, 0.2609f, 1.9333f, 0.0f, 2.6667f, 0.0f)
                horizontalLineTo(12.0f)
                verticalLineTo(2.6667f)
                horizontalLineTo(2.6667f)
                verticalLineTo(21.3333f)
                horizontalLineTo(12.0f)
                verticalLineTo(24.0f)
                horizontalLineTo(2.6667f)
                close()
                moveTo(17.3333f, 18.6667f)
                lineTo(15.5f, 16.7333f)
                lineTo(18.9f, 13.3333f)
                horizontalLineTo(8.0f)
                verticalLineTo(10.6667f)
                horizontalLineTo(18.9f)
                lineTo(15.5f, 7.2667f)
                lineTo(17.3333f, 5.3333f)
                lineTo(24.0f, 12.0f)
                lineTo(17.3333f, 18.6667f)
                close()
            }
        }.build()

        return _iconArrowRightFromBracket!!
    }

private var _iconArrowRightFromBracket: ImageVector? = null
