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

public val MyIconPack.IconLogoApple: ImageVector
    get() {
        if (_iconLogoApple != null) {
            return _iconLogoApple!!
        }
        _iconLogoApple = Builder(name = "IconLogoApple", defaultWidth = 14.0.dp, defaultHeight =
                16.0.dp, viewportWidth = 14.0f, viewportHeight = 16.0f).apply {
            path(fill = SolidColor(Color(0xFF252525)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(11.2413f, 8.4551f)
                curveTo(11.2342f, 7.1442f, 11.8272f, 6.1547f, 13.0274f, 5.426f)
                curveTo(12.3558f, 4.4651f, 11.3413f, 3.9364f, 10.0018f, 3.8328f)
                curveTo(8.7337f, 3.7328f, 7.3478f, 4.5723f, 6.8405f, 4.5723f)
                curveTo(6.3047f, 4.5723f, 5.0759f, 3.8686f, 4.1115f, 3.8686f)
                curveTo(2.1182f, 3.9007f, 0.0f, 5.4581f, 0.0f, 8.6266f)
                curveTo(0.0f, 9.5625f, 0.1715f, 10.5293f, 0.5144f, 11.5271f)
                curveTo(0.9716f, 12.8381f, 2.6219f, 16.0529f, 4.3436f, 15.9993f)
                curveTo(5.2438f, 15.9779f, 5.8796f, 15.3599f, 7.0513f, 15.3599f)
                curveTo(8.1872f, 15.3599f, 8.7766f, 15.9993f, 9.7803f, 15.9993f)
                curveTo(11.5164f, 15.9743f, 13.0095f, 13.0524f, 13.4453f, 11.7378f)
                curveTo(11.1163f, 10.6412f, 11.2413f, 8.523f, 11.2413f, 8.4551f)
                close()
                moveTo(9.2195f, 2.5898f)
                curveTo(10.1947f, 1.4324f, 10.1054f, 0.3786f, 10.0768f, 0.0f)
                curveTo(9.216f, 0.05f, 8.2193f, 0.5858f, 7.6514f, 1.2467f)
                curveTo(7.0263f, 1.9539f, 6.6583f, 2.8291f, 6.7369f, 3.815f)
                curveTo(7.6693f, 3.8864f, 8.5194f, 3.4078f, 9.2195f, 2.5898f)
                close()
            }
        }
        .build()
        return _iconLogoApple!!
    }

private var _iconLogoApple: ImageVector? = null
