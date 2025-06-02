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

public val MyIconPack.IconFillPersonRunning: ImageVector
    get() {
        if (_iconFillPersonRunning != null) {
            return _iconFillPersonRunning!!
        }
        _iconFillPersonRunning = Builder(
            name = "IconFillPersonRunning",
            defaultWidth = 18.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 18.0f,
            viewportHeight = 24.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF252525)),
                stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(11.8326f, 4.4651f)
                curveTo(13.0605f, 4.4651f, 14.0651f, 3.4605f, 14.0651f, 2.2326f)
                curveTo(14.0651f, 1.0046f, 13.0605f, 0.0f, 11.8326f, 0.0f)
                curveTo(10.6047f, 0.0f, 9.6f, 1.0046f, 9.6f, 2.2326f)
                curveTo(9.6f, 3.4605f, 10.6047f, 4.4651f, 11.8326f, 4.4651f)
                close()
                moveTo(7.814f, 19.9814f)
                lineTo(8.9302f, 15.0698f)
                lineTo(11.2744f, 17.3023f)
                verticalLineTo(24.0f)
                horizontalLineTo(13.507f)
                verticalLineTo(15.6279f)
                lineTo(11.1628f, 13.3953f)
                lineTo(11.8326f, 10.0465f)
                curveTo(13.2837f, 11.7209f, 15.5163f, 12.8372f, 17.9721f, 12.8372f)
                verticalLineTo(10.6047f)
                curveTo(15.8512f, 10.6047f, 14.0651f, 9.4884f, 13.1721f, 7.9256f)
                lineTo(12.0558f, 6.1395f)
                curveTo(11.6093f, 5.4698f, 10.9395f, 5.0233f, 10.1581f, 5.0233f)
                curveTo(9.8233f, 5.0233f, 9.6f, 5.1349f, 9.2651f, 5.1349f)
                lineTo(3.4605f, 7.5907f)
                verticalLineTo(12.8372f)
                horizontalLineTo(5.693f)
                verticalLineTo(9.0419f)
                lineTo(7.7023f, 8.2605f)
                lineTo(5.9163f, 17.3023f)
                lineTo(0.4465f, 16.186f)
                lineTo(0.0f, 18.4186f)
                lineTo(7.814f, 19.9814f)
                close()
            }
        }.build()

        return _iconFillPersonRunning!!
    }

private var _iconFillPersonRunning: ImageVector? = null
