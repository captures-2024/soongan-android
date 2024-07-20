package com.captures2024.soongan.core.designsystem.icon.myiconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.designsystem.icon.MyIconPack

public val MyIconPack.IconNonFillPlus: ImageVector
    get() {
        if (_iconNonFillPlus != null) {
            return _iconNonFillPlus!!
        }
        _iconNonFillPlus = Builder(name = "IconNonFillPlus", defaultWidth = 28.0.dp, defaultHeight =
        28.0.dp, viewportWidth = 28.0f, viewportHeight = 28.0f).apply {
            path(fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(15.4f, 0.0f)
                horizontalLineTo(12.6f)
                verticalLineTo(12.6f)
                horizontalLineTo(0.0f)
                verticalLineTo(15.4f)
                horizontalLineTo(12.6f)
                verticalLineTo(28.0f)
                horizontalLineTo(15.4f)
                verticalLineTo(15.4f)
                horizontalLineTo(28.0f)
                verticalLineTo(12.6f)
                horizontalLineTo(15.4f)
                verticalLineTo(0.0f)
                close()
            }
        }
        .build()
        return _iconNonFillPlus!!
    }

private var _iconNonFillPlus: ImageVector? = null
