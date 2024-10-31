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

public val MyIconPack.IconNonSelectedProfile: ImageVector
    get() {
        if (_iconNonSelectedProfile != null) {
            return _iconNonSelectedProfile!!
        }
        _iconNonSelectedProfile = Builder(
            name = "IconNonSelectedProfile",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFF252525)),
                stroke = null,
                fillAlpha = 0.3f,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(12.0f, 3.0f)
                curveTo(13.65f, 3.0f, 15.0f, 4.35f, 15.0f, 6.0f)
                curveTo(15.0f, 7.65f, 13.65f, 9.0f, 12.0f, 9.0f)
                curveTo(10.35f, 9.0f, 9.0f, 7.65f, 9.0f, 6.0f)
                curveTo(9.0f, 4.35f, 10.35f, 3.0f, 12.0f, 3.0f)
                close()
                moveTo(12.0f, 18.0f)
                curveTo(16.05f, 18.0f, 20.7f, 19.935f, 21.0f, 21.0f)
                horizontalLineTo(3.0f)
                curveTo(3.345f, 19.92f, 7.965f, 18.0f, 12.0f, 18.0f)
                close()
                moveTo(12.0f, 0.0f)
                curveTo(8.685f, 0.0f, 6.0f, 2.685f, 6.0f, 6.0f)
                curveTo(6.0f, 9.315f, 8.685f, 12.0f, 12.0f, 12.0f)
                curveTo(15.315f, 12.0f, 18.0f, 9.315f, 18.0f, 6.0f)
                curveTo(18.0f, 2.685f, 15.315f, 0.0f, 12.0f, 0.0f)
                close()
                moveTo(12.0f, 15.0f)
                curveTo(7.995f, 15.0f, 0.0f, 17.01f, 0.0f, 21.0f)
                verticalLineTo(24.0f)
                horizontalLineTo(24.0f)
                verticalLineTo(21.0f)
                curveTo(24.0f, 17.01f, 16.005f, 15.0f, 12.0f, 15.0f)
                close()
            }
        }.build()

        return _iconNonSelectedProfile!!
    }

private var _iconNonSelectedProfile: ImageVector? = null
