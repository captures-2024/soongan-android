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

public val MyIconPack.IconComment: ImageVector
    get() {
        if (_iconComment != null) {
            return _iconComment!!
        }
        _iconComment = Builder(name = "IconComment", defaultWidth = 24.0.dp, defaultHeight =
                24.0.dp, viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF252525)),
                    strokeLineWidth = 1.5f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(23.238f, 2.4f)
                verticalLineTo(2.4004f)
                lineTo(23.249f, 22.1883f)
                lineTo(19.7303f, 18.6697f)
                lineTo(19.5107f, 18.45f)
                horizontalLineTo(19.2f)
                horizontalLineTo(2.4f)
                curveTo(1.4942f, 18.45f, 0.75f, 17.7058f, 0.75f, 16.8f)
                verticalLineTo(2.4f)
                curveTo(0.75f, 1.4942f, 1.4942f, 0.75f, 2.4f, 0.75f)
                horizontalLineTo(21.6f)
                curveTo(22.5009f, 0.75f, 23.238f, 1.4893f, 23.238f, 2.4f)
                close()
            }
        }
        .build()
        return _iconComment!!
    }

private var _iconComment: ImageVector? = null
