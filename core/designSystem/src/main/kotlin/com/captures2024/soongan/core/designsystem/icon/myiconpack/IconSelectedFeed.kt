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

public val MyIconPack.IconSelectedFeed: ImageVector
    get() {
        if (_iconSelectedFeed != null) {
            return _iconSelectedFeed!!
        }
        _iconSelectedFeed = Builder(name = "IconSelectedFeed", defaultWidth = 24.0.dp, defaultHeight
                = 24.0.dp, viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF252525)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(21.3333f, 2.6667f)
                verticalLineTo(21.3333f)
                horizontalLineTo(2.6667f)
                verticalLineTo(2.6667f)
                horizontalLineTo(21.3333f)
                close()
                moveTo(21.3333f, 0.0f)
                horizontalLineTo(2.6667f)
                curveTo(1.2f, 0.0f, 0.0f, 1.2f, 0.0f, 2.6667f)
                verticalLineTo(21.3333f)
                curveTo(0.0f, 22.8f, 1.2f, 24.0f, 2.6667f, 24.0f)
                horizontalLineTo(21.3333f)
                curveTo(22.8f, 24.0f, 24.0f, 22.8f, 24.0f, 21.3333f)
                verticalLineTo(2.6667f)
                curveTo(24.0f, 1.2f, 22.8f, 0.0f, 21.3333f, 0.0f)
                close()
                moveTo(14.8533f, 11.8133f)
                lineTo(10.8533f, 16.9733f)
                lineTo(8.0f, 13.52f)
                lineTo(4.0f, 18.6667f)
                horizontalLineTo(20.0f)
                lineTo(14.8533f, 11.8133f)
                close()
            }
        }
        .build()
        return _iconSelectedFeed!!
    }

private var _iconSelectedFeed: ImageVector? = null
