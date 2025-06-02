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

public val MyIconPack.IconNonFillBell: ImageVector
    get() {
        if (_iconNonFillBell != null) {
            return _iconNonFillBell!!
        }
        _iconNonFillBell = Builder(
            name = "IconNonFillBell",
            defaultWidth = 18.0.dp,
            defaultHeight = 20.0.dp,
            viewportWidth = 18.0f,
            viewportHeight = 20.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF252525)),
                stroke = null,
                fillAlpha = 0.9f,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(7.0f, 18.0f)
                horizontalLineTo(11.0f)
                curveTo(11.0f, 19.0476f, 10.1193f, 20.0f, 9.0717f, 20.0f)
                curveTo(8.0241f, 20.0f, 7.0f, 19.0476f, 7.0f, 18.0f)
                close()
                moveTo(16.0f, 15.0f)
                horizontalLineTo(18.0f)
                lineTo(18.0f, 17.0f)
                horizontalLineTo(0.0f)
                lineTo(0.0f, 15.0f)
                horizontalLineTo(2.0f)
                lineTo(2.0f, 14.2857f)
                verticalLineTo(8.5714f)
                curveTo(2.0f, 5.6191f, 4.3098f, 2.8571f, 7.167f, 2.0f)
                verticalLineTo(1.9048f)
                curveTo(7.167f, 0.8571f, 8.0241f, 0.0f, 9.0717f, 0.0f)
                curveTo(10.1193f, 0.0f, 10.9765f, 0.8571f, 10.9765f, 1.9048f)
                verticalLineTo(2.0f)
                curveTo(13.8336f, 2.8571f, 16.0f, 5.6191f, 16.0f, 8.5714f)
                verticalLineTo(14.2857f)
                verticalLineTo(15.0f)
                close()
                moveTo(14.0f, 8.5714f)
                curveTo(14.0f, 5.9048f, 11.7384f, 3.8095f, 9.0717f, 3.8095f)
                curveTo(6.4051f, 3.8095f, 4.0f, 5.9048f, 4.0f, 8.5714f)
                lineTo(4.0f, 15.0f)
                horizontalLineTo(14.0f)
                lineTo(14.0f, 8.5714f)
                close()
            }
        }.build()
        return _iconNonFillBell!!
    }

private var _iconNonFillBell: ImageVector? = null
