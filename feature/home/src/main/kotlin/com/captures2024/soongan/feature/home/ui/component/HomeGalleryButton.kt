package com.captures2024.soongan.feature.home.ui.component

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
        .dropShadow(
            shape = CircleShape,
            color = Color(0xFF000000).copy(alpha = 0.25f),
            blur = 4.dp,
            offsetX = 0.dp,
            offsetY = 2.dp
        ),
    shape = CircleShape,
    contentPadding = PaddingValues(0.dp),
    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
    content = content
)

@DevicePreviews
@Composable
private fun HomeGalleryButtonPreview() {
    HomeGalleryButton(onClick = { /*TODO*/ }) {

    }
}