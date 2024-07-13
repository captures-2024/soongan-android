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
        _iconFillHeart = Builder(name = "IconFillHeart", defaultWidth = 24.0.dp, defaultHeight =
                21.0.dp, viewportWidth = 24.0f, viewportHeight = 21.0f).apply {
            path(fill = SolidColor(Color(0xFF252525)), stroke = SolidColor(Color(0xFF252525)),
                    strokeLineWidth = 1.5f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(10.7517f, 18.923f)
                lineTo(10.7506f, 18.9221f)
                curveTo(7.645f, 16.2364f, 5.1297f, 14.0596f, 3.3818f, 12.0216f)
                curveTo(1.6412f, 9.9922f, 0.75f, 8.199f, 0.75f, 6.2943f)
                curveTo(0.75f, 3.2168f, 3.2843f, 0.75f, 6.6f, 0.75f)
                curveTo(8.4739f, 0.75f, 10.2745f, 1.5855f, 11.4406f, 2.8914f)
                lineTo(12.0f, 3.5179f)
                lineTo(12.5594f, 2.8914f)
                curveTo(13.7255f, 1.5855f, 15.5261f, 0.75f, 17.4f, 0.75f)
                curveTo(20.7157f, 0.75f, 23.25f, 3.2168f, 23.25f, 6.2943f)
                curveTo(23.25f, 8.199f, 22.3588f, 9.9922f, 20.618f, 12.0232f)
                curveTo(18.871f, 14.0616f, 16.3576f, 16.2398f, 13.2544f, 18.9292f)
                lineTo(13.2504f, 18.9326f)
                lineTo(13.2488f, 18.934f)
                lineTo(12.0019f, 20.0084f)
                lineTo(10.7517f, 18.923f)
                close()
            }
        }
        .build()
        return _iconFillHeart!!
    }

private var _iconFillHeart: ImageVector? = null
