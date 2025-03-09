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

public val MyIconPack.IconSelectedAwards: ImageVector
    get() {
        if (_iconSelectedAwards != null) {
            return _iconSelectedAwards!!
        }
        _iconSelectedAwards = Builder(
            name = "IconSelectedAwards",
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
                moveTo(5.3333f, 24.0f)
                verticalLineTo(21.3333f)
                horizontalLineTo(10.6667f)
                verticalLineTo(17.2f)
                curveTo(9.5778f, 16.9556f, 8.6056f, 16.4944f, 7.75f, 15.8167f)
                curveTo(6.8944f, 15.1389f, 6.2667f, 14.2889f, 5.8667f, 13.2667f)
                curveTo(4.2f, 13.0667f, 2.8056f, 12.3389f, 1.6833f, 11.0833f)
                curveTo(0.5611f, 9.8278f, 0.0f, 8.3556f, 0.0f, 6.6667f)
                verticalLineTo(5.3333f)
                curveTo(0.0f, 4.6f, 0.2611f, 3.9722f, 0.7833f, 3.45f)
                curveTo(1.3056f, 2.9278f, 1.9333f, 2.6667f, 2.6667f, 2.6667f)
                horizontalLineTo(5.3333f)
                verticalLineTo(0.0f)
                horizontalLineTo(18.6667f)
                verticalLineTo(2.6667f)
                horizontalLineTo(21.3333f)
                curveTo(22.0667f, 2.6667f, 22.6944f, 2.9278f, 23.2167f, 3.45f)
                curveTo(23.7389f, 3.9722f, 24.0f, 4.6f, 24.0f, 5.3333f)
                verticalLineTo(6.6667f)
                curveTo(24.0f, 8.3556f, 23.4389f, 9.8278f, 22.3167f, 11.0833f)
                curveTo(21.1944f, 12.3389f, 19.8f, 13.0667f, 18.1333f, 13.2667f)
                curveTo(17.7333f, 14.2889f, 17.1056f, 15.1389f, 16.25f, 15.8167f)
                curveTo(15.3944f, 16.4944f, 14.4222f, 16.9556f, 13.3333f, 17.2f)
                verticalLineTo(21.3333f)
                horizontalLineTo(18.6667f)
                verticalLineTo(24.0f)
                horizontalLineTo(5.3333f)
                close()
                moveTo(5.3333f, 10.4f)
                verticalLineTo(5.3333f)
                horizontalLineTo(2.6667f)
                verticalLineTo(6.6667f)
                curveTo(2.6667f, 7.5111f, 2.9111f, 8.2722f, 3.4f, 8.95f)
                curveTo(3.8889f, 9.6278f, 4.5333f, 10.1111f, 5.3333f, 10.4f)
                close()
                moveTo(12.0f, 14.6667f)
                curveTo(13.1111f, 14.6667f, 14.0556f, 14.2778f, 14.8333f, 13.5f)
                curveTo(15.6111f, 12.7222f, 16.0f, 11.7778f, 16.0f, 10.6667f)
                verticalLineTo(2.6667f)
                horizontalLineTo(8.0f)
                verticalLineTo(10.6667f)
                curveTo(8.0f, 11.7778f, 8.3889f, 12.7222f, 9.1667f, 13.5f)
                curveTo(9.9444f, 14.2778f, 10.8889f, 14.6667f, 12.0f, 14.6667f)
                close()
                moveTo(18.6667f, 10.4f)
                curveTo(19.4667f, 10.1111f, 20.1111f, 9.6278f, 20.6f, 8.95f)
                curveTo(21.0889f, 8.2722f, 21.3333f, 7.5111f, 21.3333f, 6.6667f)
                verticalLineTo(5.3333f)
                horizontalLineTo(18.6667f)
                verticalLineTo(10.4f)
                close()
            }
        }.build()

        return _iconSelectedAwards!!
    }

private var _iconSelectedAwards: ImageVector? = null
