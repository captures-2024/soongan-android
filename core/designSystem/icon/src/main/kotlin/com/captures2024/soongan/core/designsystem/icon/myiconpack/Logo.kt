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

public val MyIconPack.Logo: ImageVector
    get() {
        if (_logo != null) {
            return _logo!!
        }
        _logo = Builder(
            name = "Logo",
            defaultWidth = 37.0.dp,
            defaultHeight = 51.0.dp,
            viewportWidth = 37.0f,
            viewportHeight = 51.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFBC304)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(6.0f, 9.0f)
                moveToRelative(-4.0f, 0.0f)
                arcToRelative(4.0f, 4.0f, 0.0f, true, true, 8.0f, 0.0f)
                arcToRelative(4.0f, 4.0f, 0.0f, true, true, -8.0f, 0.0f)
            }
            path(
                fill = SolidColor(Color(0xFF252525)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(34.0982f, 9.0327f)
                curveTo(29.1095f, 4.0441f, 21.0214f, 4.0441f, 16.0327f, 9.0327f)
                curveTo(11.0441f, 14.0213f, 11.0441f, 22.1095f, 16.0327f, 27.0981f)
                lineTo(34.0982f, 9.0327f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF252525)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(26.066f, 23.0005f)
                curveTo(31.0546f, 27.9891f, 31.0545f, 36.0773f, 26.0659f, 41.0659f)
                curveTo(21.0773f, 46.0546f, 12.9891f, 46.0546f, 8.0005f, 41.066f)
                lineTo(26.066f, 23.0005f)
                close()
            }
        }.build()

        return _logo!!
    }

private var _logo: ImageVector? = null
