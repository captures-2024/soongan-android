package com.captures2024.soongan.presentation.designsystem.icon.myiconpack

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.presentation.designsystem.icon.MyIconPack
import kotlin.Unit

public val MyIconPack.IconNonFillDownArrow: ImageVector
    get() {
        if (_iconNonFillDownArrow != null) {
            return _iconNonFillDownArrow!!
        }
        _iconNonFillDownArrow = Builder(
            name = "IconNonFillDownArrow",
            defaultWidth = 20.0.dp,
            defaultHeight = 20.0.dp,
            viewportWidth = 20.0f,
            viewportHeight = 20.0f
        ).apply {
            path(fill = SolidColor(Color(0xFF252525)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = EvenOdd
            ) {
                moveTo(5.23f, 7.209f)
                curveTo(5.529f, 6.922f, 6.004f, 6.932f, 6.291f, 7.23f)
                lineTo(10.0f, 11.168f)
                lineTo(13.709f, 7.23f)
                curveTo(13.997f, 6.932f, 14.471f, 6.922f, 14.77f, 7.209f)
                curveTo(15.068f, 7.496f, 15.078f, 7.971f, 14.791f, 8.27f)
                lineTo(10.541f, 12.77f)
                curveTo(10.399f, 12.917f, 10.204f, 13.0f, 10.0f, 13.0f)
                curveTo(9.796f, 13.0f, 9.601f, 12.917f, 9.459f, 12.77f)
                lineTo(5.209f, 8.27f)
                curveTo(4.922f, 7.971f, 4.932f, 7.496f, 5.23f, 7.209f)
                close()
            }
        }.build()
        return _iconNonFillDownArrow!!
    }

private var _iconNonFillDownArrow: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.IconNonFillDownArrow, contentDescription = "")
    }
}
