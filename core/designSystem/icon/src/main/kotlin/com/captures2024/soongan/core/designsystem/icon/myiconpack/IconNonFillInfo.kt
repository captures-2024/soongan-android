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

public val MyIconPack.IconNonFillInfo: ImageVector
    get() {
        if (_iconNonFillInfo != null) {
            return _iconNonFillInfo!!
        }
        _iconNonFillInfo = Builder(
            name = "IconNonFillInfo",
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
                moveTo(10.8f, 6.0f)
                horizontalLineTo(13.2f)
                verticalLineTo(8.4f)
                horizontalLineTo(10.8f)
                verticalLineTo(6.0f)
                close()
                moveTo(10.8f, 10.8f)
                horizontalLineTo(13.2f)
                verticalLineTo(18.0f)
                horizontalLineTo(10.8f)
                verticalLineTo(10.8f)
                close()
                moveTo(12.0f, 0.0f)
                curveTo(5.376f, 0.0f, 0.0f, 5.376f, 0.0f, 12.0f)
                curveTo(0.0f, 18.624f, 5.376f, 24.0f, 12.0f, 24.0f)
                curveTo(18.624f, 24.0f, 24.0f, 18.624f, 24.0f, 12.0f)
                curveTo(24.0f, 5.376f, 18.624f, 0.0f, 12.0f, 0.0f)
                close()
                moveTo(12.0f, 21.6f)
                curveTo(6.708f, 21.6f, 2.4f, 17.292f, 2.4f, 12.0f)
                curveTo(2.4f, 6.708f, 6.708f, 2.4f, 12.0f, 2.4f)
                curveTo(17.292f, 2.4f, 21.6f, 6.708f, 21.6f, 12.0f)
                curveTo(21.6f, 17.292f, 17.292f, 21.6f, 12.0f, 21.6f)
                close()
            }
        }.build()

        return _iconNonFillInfo!!
    }

private var _iconNonFillInfo: ImageVector? = null
