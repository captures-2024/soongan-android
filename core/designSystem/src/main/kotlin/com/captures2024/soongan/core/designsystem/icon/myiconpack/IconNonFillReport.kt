package com.captures2024.soongan.core.designsystem.icon.myiconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.designsystem.icon.MyIconPack

public val MyIconPack.IconNonFillReport: ImageVector
    get() {
        if (_iconNonFillReport != null) {
            return _iconNonFillReport!!
        }
        _iconNonFillReport = Builder(
            name = "IconNonFillReport",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFC0000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(16.9733f, 0.0f)
                horizontalLineTo(7.0267f)
                lineTo(0.0f, 7.0267f)
                verticalLineTo(16.9733f)
                lineTo(7.0267f, 24.0f)
                horizontalLineTo(16.9733f)
                lineTo(24.0f, 16.9733f)
                verticalLineTo(7.0267f)
                lineTo(16.9733f, 0.0f)
                close()
                moveTo(21.3333f, 15.8667f)
                lineTo(15.8667f, 21.3333f)
                horizontalLineTo(8.1333f)
                lineTo(2.6667f, 15.8667f)
                verticalLineTo(8.1333f)
                lineTo(8.1333f, 2.6667f)
                horizontalLineTo(15.8667f)
                lineTo(21.3333f, 8.1333f)
                verticalLineTo(15.8667f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFFC0000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(12.0003f, 18.6667f)
                curveTo(12.7367f, 18.6667f, 13.3337f, 18.0697f, 13.3337f, 17.3333f)
                curveTo(13.3337f, 16.597f, 12.7367f, 16.0f, 12.0003f, 16.0f)
                curveTo(11.2639f, 16.0f, 10.667f, 16.597f, 10.667f, 17.3333f)
                curveTo(10.667f, 18.0697f, 11.2639f, 18.6667f, 12.0003f, 18.6667f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFC0000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(10.667f, 5.3333f)
                horizontalLineTo(13.3337f)
                verticalLineTo(14.6666f)
                horizontalLineTo(10.667f)
                verticalLineTo(5.3333f)
                close()
            }
        }
        .build()
        return _iconNonFillReport!!
    }

private var _iconNonFillReport: ImageVector? = null
