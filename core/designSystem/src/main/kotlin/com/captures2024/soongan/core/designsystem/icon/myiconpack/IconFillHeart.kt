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

public val MyIconPack.IconFillHeart: ImageVector
    get() {
        if (_iconFillHeart != null) {
            return _iconFillHeart!!
        }
        _iconFillHeart = Builder(
            name = "IconFillHeart",
            defaultWidth = 12.0.dp,
            defaultHeight = 11.0.dp,
            viewportWidth = 12.0f,
            viewportHeight = 11.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFF252525)),
                stroke = SolidColor(Color(0xFF252525)),
                strokeLineWidth = 1.5f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(5.6217f, 9.1783f)
                lineTo(5.6206f, 9.1774f)
                curveTo(4.06f, 7.8278f, 2.8272f, 6.7597f, 1.9756f, 5.7667f)
                curveTo(1.1312f, 4.7822f, 0.75f, 3.9704f, 0.75f, 3.1471f)
                curveTo(0.75f, 1.8321f, 1.8323f, 0.75f, 3.3f, 0.75f)
                curveTo(4.1299f, 0.75f, 4.9285f, 1.122f, 5.4406f, 1.6954f)
                lineTo(6.0f, 2.322f)
                lineTo(6.5594f, 1.6954f)
                curveTo(7.0715f, 1.122f, 7.8701f, 0.75f, 8.7f, 0.75f)
                curveTo(10.1677f, 0.75f, 11.25f, 1.8321f, 11.25f, 3.1471f)
                curveTo(11.25f, 3.9704f, 10.8688f, 4.7823f, 10.0243f, 5.7676f)
                curveTo(9.1726f, 6.7613f, 7.9401f, 7.8306f, 6.3796f, 9.1829f)
                curveTo(6.3794f, 9.1832f, 6.3791f, 9.1834f, 6.3788f, 9.1836f)
                lineTo(6.0019f, 9.5084f)
                lineTo(5.6217f, 9.1783f)
                close()
            }
        }.build()

        return _iconFillHeart!!
    }

private var _iconFillHeart: ImageVector? = null
