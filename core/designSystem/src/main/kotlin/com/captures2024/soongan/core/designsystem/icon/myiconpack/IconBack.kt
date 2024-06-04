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

public val MyIconPack.IconBack: ImageVector
    get() {
        if (_iconBack != null) {
            return _iconBack!!
        }
        _iconBack = Builder(name = "IconBack", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFFF5F5F5)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(17.7879f, 3.7879f)
                lineTo(16.0f, 2.0f)
                lineTo(6.0f, 12.0f)
                lineTo(16.0f, 22.0f)
                lineTo(17.7879f, 20.2121f)
                lineTo(9.5758f, 12.0f)
                lineTo(17.7879f, 3.7879f)
                close()
            }
        }
        .build()
        return _iconBack!!
    }

private var _iconBack: ImageVector? = null
