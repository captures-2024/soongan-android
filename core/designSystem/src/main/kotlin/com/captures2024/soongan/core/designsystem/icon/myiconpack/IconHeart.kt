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

public val MyIconPack.IconHeart: ImageVector
    get() {
        if (_iconHeart != null) {
            return _iconHeart!!
        }
        _iconHeart = Builder(name = "IconHeart", defaultWidth = 12.0.dp, defaultHeight = 12.0.dp,
                viewportWidth = 12.0f, viewportHeight = 12.0f).apply {
            path(fill = SolidColor(Color(0xFF252525)), stroke = SolidColor(Color(0xFF252525)),
                    strokeLineWidth = 1.5f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(5.6217f, 9.6783f)
                lineTo(5.6206f, 9.6774f)
                curveTo(4.06f, 8.3278f, 2.8272f, 7.2597f, 1.9756f, 6.2667f)
                curveTo(1.1312f, 5.2822f, 0.75f, 4.4704f, 0.75f, 3.6471f)
                curveTo(0.75f, 2.3321f, 1.8323f, 1.25f, 3.3f, 1.25f)
                curveTo(4.1299f, 1.25f, 4.9285f, 1.622f, 5.4406f, 2.1955f)
                lineTo(6.0f, 2.822f)
                lineTo(6.5594f, 2.1955f)
                curveTo(7.0715f, 1.622f, 7.8701f, 1.25f, 8.7f, 1.25f)
                curveTo(10.1677f, 1.25f, 11.25f, 2.3321f, 11.25f, 3.6471f)
                curveTo(11.25f, 4.4704f, 10.8688f, 5.2823f, 10.0243f, 6.2676f)
                curveTo(9.1726f, 7.2613f, 7.94f, 8.3306f, 6.3795f, 9.683f)
                curveTo(6.3793f, 9.6832f, 6.3791f, 9.6834f, 6.3788f, 9.6836f)
                lineTo(6.0019f, 10.0084f)
                lineTo(5.6217f, 9.6783f)
                close()
            }
        }
        .build()
        return _iconHeart!!
    }

private var _iconHeart: ImageVector? = null
