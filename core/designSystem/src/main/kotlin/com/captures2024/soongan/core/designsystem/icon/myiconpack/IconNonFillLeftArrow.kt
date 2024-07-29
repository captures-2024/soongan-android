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

public val MyIconPack.IconNonFillLeftArrow: ImageVector
    get() {
        if (_iconNonFillLeftArrow != null) {
            return _iconNonFillLeftArrow!!
        }
        _iconNonFillLeftArrow = Builder(name = "IconNonFillLeftArrow", defaultWidth = 20.0.dp,
                defaultHeight = 16.0.dp, viewportWidth = 20.0f, viewportHeight = 16.0f).apply {
            path(fill = SolidColor(Color(0xFF252525)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(7.6191f, 0.0f)
                lineTo(8.9762f, 1.4f)
                lineTo(3.6429f, 7.0f)
                lineTo(20.0f, 7.0f)
                lineTo(20.0f, 9.0f)
                lineTo(3.6429f, 9.0f)
                lineTo(8.9762f, 14.6f)
                lineTo(7.6191f, 16.0f)
                lineTo(-0.0f, 8.0f)
                lineTo(7.6191f, 0.0f)
                close()
            }
        }
        .build()
        return _iconNonFillLeftArrow!!
    }

private var _iconNonFillLeftArrow: ImageVector? = null
