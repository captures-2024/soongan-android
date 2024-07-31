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

public val MyIconPack.IconLogoKakao: ImageVector
    get() {
        if (_iconLogoKakao != null) {
            return _iconLogoKakao!!
        }
        _iconLogoKakao = Builder(name = "IconLogoKakao", defaultWidth = 18.0.dp, defaultHeight =
                16.0.dp, viewportWidth = 18.0f, viewportHeight = 16.0f).apply {
            path(fill = SolidColor(Color(0xFF252525)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(8.6607f, 0.0f)
                curveTo(10.2266f, 0.0f, 11.6749f, 0.3048f, 13.0056f, 0.9143f)
                curveTo(14.3363f, 1.5238f, 15.3883f, 2.3526f, 16.1615f, 3.4007f)
                curveTo(16.9348f, 4.4488f, 17.3214f, 5.592f, 17.3214f, 6.8304f)
                curveTo(17.3214f, 8.0688f, 16.9348f, 9.2136f, 16.1615f, 10.265f)
                curveTo(15.3883f, 11.3163f, 14.3379f, 12.1468f, 13.0104f, 12.7563f)
                curveTo(11.6829f, 13.3658f, 10.233f, 13.6705f, 8.6607f, 13.6705f)
                curveTo(8.1645f, 13.6705f, 7.6522f, 13.635f, 7.1238f, 13.5641f)
                curveTo(4.8298f, 15.1572f, 3.6086f, 15.9635f, 3.4604f, 15.9828f)
                curveTo(3.3895f, 16.0086f, 3.3219f, 16.0054f, 3.2574f, 15.9731f)
                curveTo(3.2316f, 15.9538f, 3.2123f, 15.928f, 3.1994f, 15.8957f)
                curveTo(3.1865f, 15.8635f, 3.1801f, 15.8345f, 3.1801f, 15.8086f)
                verticalLineTo(15.77f)
                curveTo(3.2188f, 15.5184f, 3.512f, 14.4703f, 4.0597f, 12.6256f)
                curveTo(2.816f, 12.0065f, 1.8285f, 11.1857f, 1.0971f, 10.1634f)
                curveTo(0.3657f, 9.1411f, 0.0f, 8.0301f, 0.0f, 6.8304f)
                curveTo(0.0f, 5.592f, 0.3866f, 4.4488f, 1.1599f, 3.4007f)
                curveTo(1.9332f, 2.3526f, 2.9852f, 1.5238f, 4.3159f, 0.9143f)
                curveTo(5.6465f, 0.3048f, 7.0948f, 0.0f, 8.6607f, 0.0f)
                close()
            }
        }
        .build()
        return _iconLogoKakao!!
    }

private var _iconLogoKakao: ImageVector? = null
