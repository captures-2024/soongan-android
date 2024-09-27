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

public val MyIconPack.IconNonFillComment: ImageVector
    get() {
        if (_iconNonFillComment != null) {
            return _iconNonFillComment!!
        }
        _iconNonFillComment = Builder(
            name = "IconNonFillComment",
            defaultWidth = 12.0.dp,
            defaultHeight = 12.0.dp,
            viewportWidth = 12.0f,
            viewportHeight = 12.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0x00000000)),
                stroke = SolidColor(Color(0xFF252525)),
                strokeLineWidth = 1.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(11.494f, 1.2f)
                verticalLineTo(1.2003f)
                lineTo(11.4993f, 10.7922f)
                lineTo(9.9536f, 9.2465f)
                lineTo(9.8071f, 9.1f)
                horizontalLineTo(9.6f)
                horizontalLineTo(1.2f)
                curveTo(0.8161f, 9.1f, 0.5f, 8.7839f, 0.5f, 8.4f)
                verticalLineTo(1.2f)
                curveTo(0.5f, 0.8161f, 0.8161f, 0.5f, 1.2f, 0.5f)
                horizontalLineTo(10.8f)
                curveTo(11.1806f, 0.5f, 11.494f, 0.8129f, 11.494f, 1.2f)
                close()
            }
        }.build()

        return _iconNonFillComment!!
    }

private var _iconNonFillComment: ImageVector? = null
