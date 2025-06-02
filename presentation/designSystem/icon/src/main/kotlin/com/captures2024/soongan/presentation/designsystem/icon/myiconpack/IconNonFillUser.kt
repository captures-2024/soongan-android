package com.captures2024.soongan.presentation.designsystem.icon.myiconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.presentation.designsystem.icon.MyIconPack

public val MyIconPack.IconNonFillUser: ImageVector
    get() {
        if (_iconNonFillUser != null) {
            return _iconNonFillUser!!
        }
        _iconNonFillUser = Builder(
            name = "IconNonFillUser",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f
        ).apply {
            path(
                fill = SolidColor(Color(0x00000000)),
                stroke = SolidColor(Color(0xFF252525)),
                strokeLineWidth = 2.5f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(1.25f, 18.1373f)
                curveTo(1.25f, 17.5754f, 1.4917f, 17.0237f, 2.0544f, 16.4627f)
                curveTo(2.6317f, 15.8872f, 3.5014f, 15.3542f, 4.6009f, 14.9015f)
                curveTo(6.7994f, 13.9962f, 9.6331f, 13.5269f, 12.0f, 13.5269f)
                curveTo(14.3669f, 13.5269f, 17.2006f, 13.9962f, 19.3991f, 14.9015f)
                curveTo(20.4986f, 15.3542f, 21.3683f, 15.8872f, 21.9456f, 16.4627f)
                curveTo(22.5083f, 17.0237f, 22.75f, 17.5754f, 22.75f, 18.1373f)
                verticalLineTo(22.7478f)
                horizontalLineTo(1.25f)
                verticalLineTo(18.1373f)
                close()
            }
            path(
                fill = SolidColor(Color(0x00000000)),
                stroke = SolidColor(Color(0xFF252525)),
                strokeLineWidth = 2.5f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(12.0f, 5.0f)
                moveToRelative(-3.75f, 0.0f)
                arcToRelative(3.75f, 3.75f, 0.0f, true, true, 7.5f, 0.0f)
                arcToRelative(3.75f, 3.75f, 0.0f, true, true, -7.5f, 0.0f)
            }
        }.build()

        return _iconNonFillUser!!
    }

private var _iconNonFillUser: ImageVector? = null
