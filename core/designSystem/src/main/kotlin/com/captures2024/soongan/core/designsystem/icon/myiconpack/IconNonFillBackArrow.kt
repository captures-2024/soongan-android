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

public val MyIconPack.IconNonFillBackArrow: ImageVector
    get() {
        if (_iconNonFillBackArrow != null) {
            return _iconNonFillBackArrow!!
        }
        _iconNonFillBackArrow = Builder(name = "IconNonFillBackArrow", defaultWidth = 12.0.dp,
                defaultHeight = 20.0.dp, viewportWidth = 12.0f, viewportHeight = 20.0f).apply {
            path(fill = SolidColor(Color(0xFFF5F5F5)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(11.7879f, 1.7879f)
                lineTo(10.0f, 0.0f)
                lineTo(0.0f, 10.0f)
                lineTo(10.0f, 20.0f)
                lineTo(11.7879f, 18.2121f)
                lineTo(3.5758f, 10.0f)
                lineTo(11.7879f, 1.7879f)
                close()
            }
        }
        .build()
        return _iconNonFillBackArrow!!
    }

private var _iconNonFillBackArrow: ImageVector? = null
