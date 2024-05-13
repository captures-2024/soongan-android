package com.captures2024.soongan.core.designsystem.icon.myiconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.designsystem.icon.MyIconPack

public val MyIconPack.LogoApple: ImageVector
    get() {
        if (_logoApple != null) {
            return _logoApple!!
        }
        _logoApple = Builder(name = "LogoApple", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFFF5F5F5)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(0.0f, 0.0f)
                horizontalLineToRelative(24.0f)
                verticalLineToRelative(24.0f)
                horizontalLineToRelative(-24.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF252525)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(16.2413f, 12.4551f)
                curveTo(16.2342f, 11.1442f, 16.8272f, 10.1547f, 18.0274f, 9.426f)
                curveTo(17.3558f, 8.4651f, 16.3413f, 7.9364f, 15.0018f, 7.8328f)
                curveTo(13.7337f, 7.7328f, 12.3478f, 8.5723f, 11.8405f, 8.5723f)
                curveTo(11.3047f, 8.5723f, 10.0759f, 7.8686f, 9.1115f, 7.8686f)
                curveTo(7.1182f, 7.9007f, 5.0f, 9.4581f, 5.0f, 12.6266f)
                curveTo(5.0f, 13.5625f, 5.1715f, 14.5293f, 5.5144f, 15.5271f)
                curveTo(5.9716f, 16.838f, 7.6219f, 20.0529f, 9.3436f, 19.9993f)
                curveTo(10.2438f, 19.9779f, 10.8796f, 19.3599f, 12.0513f, 19.3599f)
                curveTo(13.1872f, 19.3599f, 13.7766f, 19.9993f, 14.7803f, 19.9993f)
                curveTo(16.5164f, 19.9743f, 18.0095f, 17.0524f, 18.4453f, 15.7379f)
                curveTo(16.1163f, 14.6412f, 16.2413f, 12.523f, 16.2413f, 12.4551f)
                close()
                moveTo(14.2195f, 6.5898f)
                curveTo(15.1947f, 5.4324f, 15.1054f, 4.3786f, 15.0768f, 4.0f)
                curveTo(14.216f, 4.05f, 13.2194f, 4.5858f, 12.6514f, 5.2467f)
                curveTo(12.0263f, 5.9539f, 11.6584f, 6.8291f, 11.7369f, 7.815f)
                curveTo(12.6693f, 7.8864f, 13.5194f, 7.4078f, 14.2195f, 6.5898f)
                close()
            }
        }
        .build()
        return _logoApple!!
    }

private var _logoApple: ImageVector? = null
