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

public val MyIconPack.IconFilterNew: ImageVector
    get() {
        if (_iconFilterNew != null) {
            return _iconFilterNew!!
        }
        _iconFilterNew = Builder(
            name = "IconFilterNew",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
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
                moveTo(20.6364f, 2.1818f)
                horizontalLineTo(19.5455f)
                verticalLineTo(0.0f)
                horizontalLineTo(17.3636f)
                verticalLineTo(2.1818f)
                horizontalLineTo(6.4545f)
                verticalLineTo(0.0f)
                horizontalLineTo(4.2727f)
                verticalLineTo(2.1818f)
                horizontalLineTo(3.1818f)
                curveTo(1.9818f, 2.1818f, 1.0f, 3.1636f, 1.0f, 4.3636f)
                verticalLineTo(21.8182f)
                curveTo(1.0f, 23.0182f, 1.9818f, 24.0f, 3.1818f, 24.0f)
                horizontalLineTo(20.6364f)
                curveTo(21.8364f, 24.0f, 22.8182f, 23.0182f, 22.8182f, 21.8182f)
                verticalLineTo(4.3636f)
                curveTo(22.8182f, 3.1636f, 21.8364f, 2.1818f, 20.6364f, 2.1818f)
                close()
                moveTo(20.6364f, 21.8182f)
                horizontalLineTo(3.1818f)
                verticalLineTo(9.8182f)
                horizontalLineTo(20.6364f)
                verticalLineTo(21.8182f)
                close()
                moveTo(20.6364f, 7.6364f)
                horizontalLineTo(3.1818f)
                verticalLineTo(4.3636f)
                horizontalLineTo(20.6364f)
                verticalLineTo(7.6364f)
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
                moveTo(8.0f, 16.0f)
                lineTo(9.0035f, 15.0f)
                lineTo(11.0078f, 17.0f)
                lineTo(11.0078f, 11.0f)
                lineTo(13.0078f, 11.0f)
                lineTo(13.0035f, 17.0f)
                lineTo(15.0004f, 15.0f)
                lineTo(16.0f, 16.0f)
                lineTo(12.0f, 20.0f)
                lineTo(8.0f, 16.0f)
                close()
            }
        }.build()

        return _iconFilterNew!!
    }

private var _iconFilterNew: ImageVector? = null
