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

public val MyIconPack.IconHearts: ImageVector
    get() {
        if (_iconHearts != null) {
            return _iconHearts!!
        }
        _iconHearts = Builder(name = "IconHearts", defaultWidth = 16.0.dp, defaultHeight = 15.0.dp,
                viewportWidth = 16.0f, viewportHeight = 15.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF252525)),
                    strokeAlpha = 0.9f, strokeLineWidth = 1.5f, strokeLineCap = Butt, strokeLineJoin
                    = Miter, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(7.3317f, 13.0926f)
                lineTo(7.3306f, 13.0916f)
                curveTo(5.255f, 11.2966f, 3.5947f, 9.859f, 2.4443f, 8.5177f)
                curveTo(1.3012f, 7.1849f, 0.75f, 6.0459f, 0.75f, 4.8622f)
                curveTo(0.75f, 2.9597f, 2.3163f, 1.416f, 4.4f, 1.416f)
                curveTo(5.5779f, 1.416f, 6.7105f, 1.9425f, 7.4406f, 2.7601f)
                lineTo(8.0f, 3.3866f)
                lineTo(8.5594f, 2.7601f)
                curveTo(9.2895f, 1.9425f, 10.4221f, 1.416f, 11.6f, 1.416f)
                curveTo(13.6837f, 1.416f, 15.25f, 2.9597f, 15.25f, 4.8622f)
                curveTo(15.25f, 6.0459f, 14.6988f, 7.185f, 13.5555f, 8.5188f)
                curveTo(12.4051f, 9.8611f, 10.745f, 11.3005f, 8.6695f, 13.0991f)
                curveTo(8.6693f, 13.0994f, 8.6691f, 13.0996f, 8.6688f, 13.0998f)
                lineTo(8.0019f, 13.6744f)
                lineTo(7.3317f, 13.0926f)
                close()
            }
        }
        .build()
        return _iconHearts!!
    }

private var _iconHearts: ImageVector? = null
