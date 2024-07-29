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

public val MyIconPack.IconNonFillHeart: ImageVector
    get() {
        if (_iconNonFillHeart != null) {
            return _iconNonFillHeart!!
        }
        _iconNonFillHeart = Builder(name = "IconNonFillHeart", defaultWidth = 16.0.dp, defaultHeight
                = 14.0.dp, viewportWidth = 16.0f, viewportHeight = 14.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF252525)),
                    strokeAlpha = 0.9f, strokeLineWidth = 1.5f, strokeLineCap = Butt, strokeLineJoin
                    = Miter, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(7.3317f, 12.4266f)
                lineTo(7.3306f, 12.4256f)
                curveTo(5.255f, 10.6306f, 3.5947f, 9.193f, 2.4443f, 7.8517f)
                curveTo(1.3012f, 6.5189f, 0.75f, 5.3799f, 0.75f, 4.1962f)
                curveTo(0.75f, 2.2937f, 2.3163f, 0.75f, 4.4f, 0.75f)
                curveTo(5.5779f, 0.75f, 6.7105f, 1.2765f, 7.4406f, 2.0941f)
                lineTo(8.0f, 2.7206f)
                lineTo(8.5594f, 2.0941f)
                curveTo(9.2895f, 1.2765f, 10.4221f, 0.75f, 11.6f, 0.75f)
                curveTo(13.6837f, 0.75f, 15.25f, 2.2937f, 15.25f, 4.1962f)
                curveTo(15.25f, 5.3799f, 14.6988f, 6.5189f, 13.5555f, 7.8528f)
                curveTo(12.4051f, 9.1951f, 10.745f, 10.6344f, 8.6695f, 12.4331f)
                curveTo(8.6693f, 12.4333f, 8.6691f, 12.4336f, 8.6688f, 12.4338f)
                lineTo(8.0019f, 13.0084f)
                lineTo(7.3317f, 12.4266f)
                close()
            }
        }
        .build()
        return _iconNonFillHeart!!
    }

private var _iconNonFillHeart: ImageVector? = null
