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

public val MyIconPack.IconBack2: ImageVector
    get() {
        if (_iconBack2 != null) {
            return _iconBack2!!
        }
        _iconBack2 = Builder(name = "IconBack2", defaultWidth = 21.0.dp, defaultHeight = 16.0.dp,
                viewportWidth = 21.0f, viewportHeight = 16.0f).apply {
            path(fill = SolidColor(Color(0xFF252525)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(8.0f, -0.0f)
                lineTo(9.425f, 1.4f)
                lineTo(3.825f, 7.0f)
                lineTo(21.0f, 7.0f)
                lineTo(21.0f, 9.0f)
                lineTo(3.825f, 9.0f)
                lineTo(9.425f, 14.6f)
                lineTo(8.0f, 16.0f)
                lineTo(0.0f, 8.0f)
                lineTo(8.0f, -0.0f)
                close()
            }
        }
        .build()
        return _iconBack2!!
    }

private var _iconBack2: ImageVector? = null
