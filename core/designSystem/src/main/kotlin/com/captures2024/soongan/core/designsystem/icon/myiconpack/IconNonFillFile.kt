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

public val MyIconPack.IconNonFillFile: ImageVector
    get() {
        if (_iconNonFillFile != null) {
            return _iconNonFillFile!!
        }
        _iconNonFillFile = Builder(
            name = "IconNonFillFile",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(5.8889f, 16.8f)
                horizontalLineTo(14.4444f)
                verticalLineTo(19.2f)
                horizontalLineTo(5.8889f)
                verticalLineTo(16.8f)
                close()
                moveTo(5.8889f, 12.0f)
                horizontalLineTo(18.1111f)
                verticalLineTo(14.4f)
                horizontalLineTo(5.8889f)
                verticalLineTo(12.0f)
                close()
                moveTo(5.8889f, 7.2f)
                horizontalLineTo(18.1111f)
                verticalLineTo(9.6f)
                horizontalLineTo(5.8889f)
                verticalLineTo(7.2f)
                close()
                moveTo(20.5556f, 2.4f)
                horizontalLineTo(15.4467f)
                curveTo(14.9333f, 1.008f, 13.5889f, 0.0f, 12.0f, 0.0f)
                curveTo(10.4111f, 0.0f, 9.0667f, 1.008f, 8.5533f, 2.4f)
                horizontalLineTo(3.4444f)
                curveTo(3.2733f, 2.4f, 3.1144f, 2.412f, 2.9556f, 2.448f)
                curveTo(2.4789f, 2.544f, 2.0511f, 2.784f, 1.7211f, 3.108f)
                curveTo(1.5011f, 3.324f, 1.3178f, 3.588f, 1.1956f, 3.876f)
                curveTo(1.0733f, 4.152f, 1.0f, 4.464f, 1.0f, 4.8f)
                verticalLineTo(21.6f)
                curveTo(1.0f, 21.924f, 1.0733f, 22.248f, 1.1956f, 22.536f)
                curveTo(1.3178f, 22.824f, 1.5011f, 23.076f, 1.7211f, 23.304f)
                curveTo(2.0511f, 23.628f, 2.4789f, 23.868f, 2.9556f, 23.964f)
                curveTo(3.1144f, 23.988f, 3.2733f, 24.0f, 3.4444f, 24.0f)
                horizontalLineTo(20.5556f)
                curveTo(21.9f, 24.0f, 23.0f, 22.92f, 23.0f, 21.6f)
                verticalLineTo(4.8f)
                curveTo(23.0f, 3.48f, 21.9f, 2.4f, 20.5556f, 2.4f)
                close()
                moveTo(12.0f, 2.1f)
                curveTo(12.5011f, 2.1f, 12.9167f, 2.508f, 12.9167f, 3.0f)
                curveTo(12.9167f, 3.492f, 12.5011f, 3.9f, 12.0f, 3.9f)
                curveTo(11.4989f, 3.9f, 11.0833f, 3.492f, 11.0833f, 3.0f)
                curveTo(11.0833f, 2.508f, 11.4989f, 2.1f, 12.0f, 2.1f)
                close()
                moveTo(20.5556f, 21.6f)
                horizontalLineTo(3.4444f)
                verticalLineTo(4.8f)
                horizontalLineTo(20.5556f)
                verticalLineTo(21.6f)
                close()
            }
        }.build()

        return _iconNonFillFile!!
    }

private var _iconNonFillFile: ImageVector? = null
