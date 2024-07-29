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

public val MyIconPack.IconNonFillEdit: ImageVector
    get() {
        if (_iconNonFillEdit != null) {
            return _iconNonFillEdit!!
        }
        _iconNonFillEdit = Builder(name = "IconNonFillEdit", defaultWidth = 24.0.dp, defaultHeight =
                24.0.dp, viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF252525)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(0.0f, 24.0f)
                horizontalLineTo(4.9993f)
                lineTo(19.7439f, 9.2554f)
                lineTo(14.7446f, 4.2561f)
                lineTo(0.0f, 19.0007f)
                verticalLineTo(24.0f)
                close()
                moveTo(2.6663f, 20.1072f)
                lineTo(14.7446f, 8.0289f)
                lineTo(15.9711f, 9.2554f)
                lineTo(3.8928f, 21.3337f)
                horizontalLineTo(2.6663f)
                verticalLineTo(20.1072f)
                close()
            }
            path(fill = SolidColor(Color(0xFF252525)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(20.4903f, 0.3899f)
                curveTo(19.9704f, -0.13f, 19.1305f, -0.13f, 18.6106f, 0.3899f)
                lineTo(16.1709f, 2.8296f)
                lineTo(21.1702f, 7.8289f)
                lineTo(23.6099f, 5.3892f)
                curveTo(24.1298f, 4.8693f, 24.1298f, 4.0294f, 23.6099f, 3.5095f)
                lineTo(20.4903f, 0.3899f)
                close()
            }
        }
        .build()
        return _iconNonFillEdit!!
    }

private var _iconNonFillEdit: ImageVector? = null
