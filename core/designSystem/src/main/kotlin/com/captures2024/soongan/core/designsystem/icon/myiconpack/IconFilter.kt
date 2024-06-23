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

public val MyIconPack.IconFilter: ImageVector
    get() {
        if (_iconFilter != null) {
            return _iconFilter!!
        }
        _iconFilter = Builder(name = "IconFilter", defaultWidth = 20.0.dp, defaultHeight = 20.0.dp,
                viewportWidth = 20.0f, viewportHeight = 20.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(3.5416f, 4.6752f)
                curveTo(5.2249f, 6.8335f, 8.3332f, 10.8335f, 8.3332f, 10.8335f)
                lineTo(8.3332f, 15.8335f)
                curveTo(8.3332f, 16.2918f, 8.7082f, 16.6668f, 9.1666f, 16.6668f)
                horizontalLineTo(10.8332f)
                curveTo(11.2916f, 16.6668f, 11.6666f, 16.2918f, 11.6666f, 15.8335f)
                lineTo(11.6666f, 10.8335f)
                curveTo(11.6666f, 10.8335f, 14.7666f, 6.8335f, 16.4499f, 4.6752f)
                curveTo(16.5454f, 4.5521f, 16.6045f, 4.4048f, 16.6203f, 4.2498f)
                curveTo(16.6362f, 4.0949f, 16.6082f, 3.9386f, 16.5396f, 3.7988f)
                curveTo(16.471f, 3.659f, 16.3645f, 3.5412f, 16.2323f, 3.459f)
                curveTo(16.1f, 3.3767f, 15.9473f, 3.3332f, 15.7916f, 3.3335f)
                lineTo(4.1999f, 3.3335f)
                curveTo(3.5082f, 3.3335f, 3.1166f, 4.1252f, 3.5416f, 4.6752f)
                close()
            }
        }
        .build()
        return _iconFilter!!
    }

private var _iconFilter: ImageVector? = null
