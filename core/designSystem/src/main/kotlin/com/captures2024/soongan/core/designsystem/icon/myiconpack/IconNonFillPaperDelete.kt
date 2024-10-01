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

public val MyIconPack.IconNonFillPaperDelete: ImageVector
    get() {
        if (_iconNonFillPaperDelete != null) {
            return _iconNonFillPaperDelete!!
        }
        _iconNonFillPaperDelete = Builder(
            name = "IconNonFillPaperDelete",
            defaultWidth = 21.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 21.0f,
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
                moveTo(2.4f, 24.0f)
                curveTo(1.74f, 24.0f, 1.175f, 23.765f, 0.705f, 23.295f)
                curveTo(0.235f, 22.825f, 0.0f, 22.26f, 0.0f, 21.6f)
                verticalLineTo(2.4f)
                curveTo(0.0f, 1.74f, 0.235f, 1.175f, 0.705f, 0.705f)
                curveTo(1.175f, 0.235f, 1.74f, 0.0f, 2.4f, 0.0f)
                horizontalLineTo(12.0f)
                lineTo(19.2f, 7.2f)
                verticalLineTo(12.42f)
                curveTo(18.82f, 12.28f, 18.43f, 12.175f, 18.03f, 12.105f)
                curveTo(17.63f, 12.035f, 17.22f, 12.0f, 16.8f, 12.0f)
                verticalLineTo(8.4f)
                horizontalLineTo(10.8f)
                verticalLineTo(2.4f)
                horizontalLineTo(2.4f)
                verticalLineTo(21.6f)
                horizontalLineTo(10.02f)
                curveTo(10.18f, 22.06f, 10.38f, 22.49f, 10.62f, 22.89f)
                curveTo(10.86f, 23.29f, 11.14f, 23.66f, 11.46f, 24.0f)
                horizontalLineTo(2.4f)
                close()
                moveTo(14.28f, 23.4f)
                lineTo(12.6f, 21.72f)
                lineTo(15.12f, 19.2f)
                lineTo(12.6f, 16.68f)
                lineTo(14.28f, 15.0f)
                lineTo(16.8f, 17.52f)
                lineTo(19.32f, 15.0f)
                lineTo(21.0f, 16.68f)
                lineTo(18.51f, 19.2f)
                lineTo(21.0f, 21.72f)
                lineTo(19.32f, 23.4f)
                lineTo(16.8f, 20.91f)
                lineTo(14.28f, 23.4f)
                close()
            }
        }.build()

        return _iconNonFillPaperDelete!!
    }

private var _iconNonFillPaperDelete: ImageVector? = null
