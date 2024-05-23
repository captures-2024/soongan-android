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

public val MyIconPack.LogoKakao: ImageVector
    get() {
        if (_logoKakao != null) {
            return _logoKakao!!
        }
        _logoKakao = Builder(name = "LogoKakao", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
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
                moveTo(11.6607f, 4.0f)
                curveTo(13.2266f, 4.0f, 14.6749f, 4.3048f, 16.0056f, 4.9143f)
                curveTo(17.3363f, 5.5238f, 18.3883f, 6.3526f, 19.1615f, 7.4007f)
                curveTo(19.9348f, 8.4488f, 20.3214f, 9.592f, 20.3214f, 10.8304f)
                curveTo(20.3214f, 12.0688f, 19.9348f, 13.2136f, 19.1615f, 14.265f)
                curveTo(18.3883f, 15.3163f, 17.3379f, 16.1468f, 16.0104f, 16.7563f)
                curveTo(14.6829f, 17.3658f, 13.233f, 17.6705f, 11.6607f, 17.6705f)
                curveTo(11.1645f, 17.6705f, 10.6522f, 17.635f, 10.1238f, 17.5641f)
                curveTo(7.8298f, 19.1572f, 6.6086f, 19.9635f, 6.4604f, 19.9828f)
                curveTo(6.3895f, 20.0086f, 6.3219f, 20.0054f, 6.2574f, 19.9731f)
                curveTo(6.2316f, 19.9538f, 6.2123f, 19.928f, 6.1994f, 19.8957f)
                curveTo(6.1865f, 19.8635f, 6.1801f, 19.8345f, 6.1801f, 19.8086f)
                verticalLineTo(19.77f)
                curveTo(6.2188f, 19.5184f, 6.512f, 18.4703f, 7.0597f, 16.6256f)
                curveTo(5.816f, 16.0065f, 4.8285f, 15.1857f, 4.0971f, 14.1634f)
                curveTo(3.3657f, 13.1411f, 3.0f, 12.0301f, 3.0f, 10.8304f)
                curveTo(3.0f, 9.592f, 3.3866f, 8.4488f, 4.1599f, 7.4007f)
                curveTo(4.9332f, 6.3526f, 5.9852f, 5.5238f, 7.3159f, 4.9143f)
                curveTo(8.6465f, 4.3048f, 10.0948f, 4.0f, 11.6607f, 4.0f)
                close()
            }
        }
        .build()
        return _logoKakao!!
    }

private var _logoKakao: ImageVector? = null
