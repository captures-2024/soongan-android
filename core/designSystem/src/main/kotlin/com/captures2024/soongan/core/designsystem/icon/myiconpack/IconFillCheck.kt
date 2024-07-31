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

public val MyIconPack.IconFillCheck: ImageVector
    get() {
        if (_iconFillCheck != null) {
            return _iconFillCheck!!
        }
        _iconFillCheck = Builder(name = "IconFillCheck", defaultWidth = 20.0.dp, defaultHeight =
                20.0.dp, viewportWidth = 20.0f, viewportHeight = 20.0f).apply {
            path(fill = SolidColor(Color(0xFFDBDBDB)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(10.0f, 0.0f)
                curveTo(4.48f, 0.0f, 0.0f, 4.48f, 0.0f, 10.0f)
                curveTo(0.0f, 15.52f, 4.48f, 20.0f, 10.0f, 20.0f)
                curveTo(15.52f, 20.0f, 20.0f, 15.52f, 20.0f, 10.0f)
                curveTo(20.0f, 4.48f, 15.52f, 0.0f, 10.0f, 0.0f)
                close()
                moveTo(8.0f, 15.0f)
                lineTo(3.0f, 10.0f)
                lineTo(4.41f, 8.59f)
                lineTo(8.0f, 12.17f)
                lineTo(15.59f, 4.58f)
                lineTo(17.0f, 6.0f)
                lineTo(8.0f, 15.0f)
                close()
            }
        }
        .build()
        return _iconFillCheck!!
    }

private var _iconFillCheck: ImageVector? = null
