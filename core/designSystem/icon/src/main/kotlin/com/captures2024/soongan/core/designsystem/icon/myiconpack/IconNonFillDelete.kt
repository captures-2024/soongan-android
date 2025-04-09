package com.captures2024.soongan.core.designsystem.icon.myiconpack

/*
* Converted using https://composables.com/svgtocompose
*/

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.designsystem.icon.MyIconPack

public val MyIconPack.IconNonFillDelete: ImageVector
    get() {
        if (_iconNonFillDelete != null) {
            return _iconNonFillDelete!!
        }
        _iconNonFillDelete = ImageVector.Builder(
            name = "IconNonFillDelete",
            defaultWidth = 20.dp,
            defaultHeight = 24.dp,
            viewportWidth = 20f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF252525)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(3.75f, 24f)
                curveTo(3.06250f, 240f, 2.47420f, 23.73910f, 1.9850f, 23.21730f)
                curveTo(1.4950f, 22.69470f, 1.250f, 22.06670f, 1.250f, 21.33330f)
                verticalLineTo(4f)
                horizontalLineTo(0f)
                verticalLineTo(1.33333f)
                horizontalLineTo(6.25f)
                verticalLineTo(0f)
                horizontalLineTo(13.75f)
                verticalLineTo(1.33333f)
                horizontalLineTo(20f)
                verticalLineTo(4f)
                horizontalLineTo(18.75f)
                verticalLineTo(21.3333f)
                curveTo(18.750f, 22.06670f, 18.50540f, 22.69470f, 18.01630f, 23.21730f)
                curveTo(17.52630f, 23.73910f, 16.93750f, 240f, 16.250f, 240f)
                horizontalLineTo(3.75f)
                close()
                moveTo(16.25f, 4f)
                horizontalLineTo(3.75f)
                verticalLineTo(21.3333f)
                horizontalLineTo(16.25f)
                verticalLineTo(4f)
                close()
                moveTo(6.25f, 18.6667f)
                horizontalLineTo(8.75f)
                verticalLineTo(6.66667f)
                horizontalLineTo(6.25f)
                verticalLineTo(18.6667f)
                close()
                moveTo(11.25f, 18.6667f)
                horizontalLineTo(13.75f)
                verticalLineTo(6.66667f)
                horizontalLineTo(11.25f)
                verticalLineTo(18.6667f)
                close()
            }
        }.build()
        return _iconNonFillDelete!!
    }

private var _iconNonFillDelete: ImageVector? = null
