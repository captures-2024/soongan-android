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

public val MyIconPack.IconNonFillTopArrow: ImageVector
    get() {
        if (_iconNonFillTopArrow != null) {
            return _iconNonFillTopArrow!!
        }
        _iconNonFillTopArrow = Builder(
            name = "IconNonFillTopArrow",
            defaultWidth = 16.0.dp,
            defaultHeight = 20.0.dp,
            viewportWidth = 16.0f,
            viewportHeight = 20.0f,
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
                moveTo(16.0f, 7.6191f)
                lineTo(14.6f, 8.9762f)
                lineTo(9.0f, 3.6429f)
                lineTo(9.0f, 20.0f)
                lineTo(7.0f, 20.0f)
                lineTo(7.0f, 3.6429f)
                lineTo(1.4f, 8.9762f)
                lineTo(0.0f, 7.6191f)
                lineTo(8.0f, -0.0f)
                lineTo(16.0f, 7.6191f)
                close()
            }
        }.build()

        return _iconNonFillTopArrow!!
    }

private var _iconNonFillTopArrow: ImageVector? = null
