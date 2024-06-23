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

public val MyIconPack.IconTopArrow: ImageVector
    get() {
        if (_iconTopArrow != null) {
            return _iconTopArrow!!
        }
        _iconTopArrow = Builder(name = "IconTopArrow", defaultWidth = 16.0.dp, defaultHeight =
                21.0.dp, viewportWidth = 16.0f, viewportHeight = 21.0f).apply {
            path(fill = SolidColor(Color(0xFF252525)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(16.0f, 8.0f)
                lineTo(14.6f, 9.425f)
                lineTo(9.0f, 3.825f)
                lineTo(9.0f, 21.0f)
                lineTo(7.0f, 21.0f)
                lineTo(7.0f, 3.825f)
                lineTo(1.4f, 9.425f)
                lineTo(0.0f, 8.0f)
                lineTo(8.0f, 0.0f)
                lineTo(16.0f, 8.0f)
                close()
            }
        }
        .build()
        return _iconTopArrow!!
    }

private var _iconTopArrow: ImageVector? = null
