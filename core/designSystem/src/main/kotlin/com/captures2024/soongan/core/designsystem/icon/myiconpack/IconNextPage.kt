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

public val MyIconPack.IconNextPage: ImageVector
    get() {
        if (_iconNextPage != null) {
            return _iconNextPage!!
        }
        _iconNextPage = Builder(name = "IconNextPage", defaultWidth = 52.0.dp, defaultHeight = 52.0.dp,
                viewportWidth = 52.0f, viewportHeight = 52.0f).apply {
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
                moveTo(28.3809f, 32.0f)
                lineTo(27.0238f, 30.6f)
                lineTo(32.3571f, 25.0f)
                lineTo(16.0f, 25.0f)
                lineTo(16.0f, 23.0f)
                lineTo(32.3571f, 23.0f)
                lineTo(27.0238f, 17.4f)
                lineTo(28.3809f, 16.0f)
                lineTo(36.0f, 24.0f)
                lineTo(28.3809f, 32.0f)
                close()
            }
        }
        .build()
        return _iconNextPage!!
    }

private var _iconNextPage: ImageVector? = null
