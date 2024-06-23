package com.captures2024.soongan.feature.home.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.designsystem.theme.dropShadow
import com.captures2024.soongan.core.designsystem.theme.innerShadow
import com.captures2024.soongan.core.designsystem.util.DevicePreviews

@Composable
internal fun HomeGalleryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) = Button(
    onClick = onClick,
    modifier = modifier
        .size(44.dp)
        .innerShadow(
            shape = CircleShape,
            color = Color(0x4DFFFFFF),
            blur = 2.dp,
            offsetX = 1.dp,
            offsetY = 1.dp,
            spread = 0.dp
        )
        .innerShadow(
            shape = CircleShape,
            color = Color(0x80D4D4D4),
            blur = 2.dp,
            offsetX = (-1).dp,
            offsetY = (-1).dp,
            spread = 0.dp
        )
        .dropShadow(
            shape = CircleShape,
            color = Color(0x33D4D4D4),
            blur = 10.dp,
            offsetX = (-5).dp,
            offsetY = 5.dp
        )
        .dropShadow(
            shape = CircleShape,
            color = Color(0x33D4D4D4),
            blur = 10.dp,
            offsetX = 5.dp,
            offsetY = (-5).dp
        )
        .dropShadow(
            shape = CircleShape,
            color = Color(0xE6FFFFFF),
            blur = 10.dp,
            offsetX = (-5).dp,
            offsetY = (-5).dp
        )
        .dropShadow(
            shape = CircleShape,
            color = Color(0xE6D4D4D4),
            blur = 13.dp,
            offsetX = 5.dp,
            offsetY = 5.dp
        ),
    shape = CircleShape,
    contentPadding = PaddingValues(0.dp),
    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEBEBEB)),
    content = content
)

@DevicePreviews
@Composable
private fun HomeGalleryButtonPreview() {
    HomeGalleryButton(onClick = { /*TODO*/ }) {

    }
}