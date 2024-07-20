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

public val MyIconPack.IconSelectedHome: ImageVector
    get() {
        if (_iconSelectedHome != null) {
            return _iconSelectedHome!!
        }
        _iconSelectedHome = Builder(name = "IconSelectedHome", defaultWidth = 24.0.dp, defaultHeight
                = 24.0.dp, viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF252525)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(0.0f, 24.0f)
                verticalLineTo(8.0f)
                lineTo(12.0f, 0.0f)
                lineTo(24.0f, 8.0f)
                verticalLineTo(24.0f)
                horizontalLineTo(15.0f)
                verticalLineTo(14.6667f)
                horizontalLineTo(9.0f)
                verticalLineTo(24.0f)
                horizontalLineTo(0.0f)
                close()
                moveTo(3.0f, 21.3333f)
                horizontalLineTo(6.0f)
                verticalLineTo(12.0f)
                horizontalLineTo(18.0f)
                verticalLineTo(21.3333f)
                horizontalLineTo(21.0f)
                verticalLineTo(9.3333f)
                lineTo(12.0f, 3.3333f)
                lineTo(3.0f, 9.3333f)
                verticalLineTo(21.3333f)
                close()
            }
        }
        .build()
        return _iconSelectedHome!!
    }

private var _iconSelectedHome: ImageVector? = null
