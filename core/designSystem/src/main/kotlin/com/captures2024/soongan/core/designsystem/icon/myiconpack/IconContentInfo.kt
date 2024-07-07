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

public val MyIconPack.IconContentInfo: ImageVector
    get() {
        if (_icContentInfo != null) {
            return _icContentInfo!!
        }
        _icContentInfo = Builder(name = "IcContentInfo", defaultWidth = 52.0.dp, defaultHeight =
                52.0.dp, viewportWidth = 52.0f, viewportHeight = 52.0f).apply {
            path(fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(26.0f, 24.0f)
                moveToRelative(22.0f, -0.0f)
                arcToRelative(22.0f, 22.0f, 0.0f, true, true, -44.0f, -0.0f)
                arcToRelative(22.0f, 22.0f, 0.0f, true, true, 44.0f, -0.0f)
            }
            path(fill = SolidColor(Color(0xFF252525)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(24.8f, 18.0f)
                horizontalLineTo(27.2f)
                verticalLineTo(20.4f)
                horizontalLineTo(24.8f)
                verticalLineTo(18.0f)
                close()
                moveTo(24.8f, 22.8f)
                horizontalLineTo(27.2f)
                verticalLineTo(30.0f)
                horizontalLineTo(24.8f)
                verticalLineTo(22.8f)
                close()
                moveTo(26.0f, 12.0f)
                curveTo(19.376f, 12.0f, 14.0f, 17.376f, 14.0f, 24.0f)
                curveTo(14.0f, 30.624f, 19.376f, 36.0f, 26.0f, 36.0f)
                curveTo(32.624f, 36.0f, 38.0f, 30.624f, 38.0f, 24.0f)
                curveTo(38.0f, 17.376f, 32.624f, 12.0f, 26.0f, 12.0f)
                close()
                moveTo(26.0f, 33.6f)
                curveTo(20.708f, 33.6f, 16.4f, 29.292f, 16.4f, 24.0f)
                curveTo(16.4f, 18.708f, 20.708f, 14.4f, 26.0f, 14.4f)
                curveTo(31.292f, 14.4f, 35.6f, 18.708f, 35.6f, 24.0f)
                curveTo(35.6f, 29.292f, 31.292f, 33.6f, 26.0f, 33.6f)
                close()
            }
        }
        .build()
        return _icContentInfo!!
    }

private var _icContentInfo: ImageVector? = null
