package com.captures2024.soongan.presentation.feature.main.post.component.info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.theme.dropShadow
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.post.R

@Composable
internal fun PostInfoImageComponent(
    model: Any?,
    modifier: Modifier = Modifier,
) {
    val showShimmer = remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .width(360.dp)
            .height(460.dp)
            .dropShadow(
                shape = RoundedCornerShape(0.dp),
                color = SGColor.Grayscale.black100.copy(alpha = 0.3f),
                blur = 3.dp,
                offsetX = 6.dp,
                offsetY = 6.dp,
            )
            .dropShadow(
                shape = RectangleShape,
                color = SGColor.Grayscale.black100.copy(alpha = 0.3f),
                blur = 3.dp,
                offsetX = 6.dp,
                offsetY = 6.dp,
            )
            .background(SGColor.white),
        contentAlignment = Alignment.Center,
    ) {
        AsyncImage(
            model = model,
            contentDescription = stringResource(R.string.image_description),
            onState = {
                when (it) {
                    is AsyncImagePainter.State.Success -> showShimmer.value = false
                    else -> Unit
                }
            },
            modifier = modifier
                .widthIn(max = 360.dp)
                .heightIn(max = 460.dp),
            contentScale = ContentScale.Fit,
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewPostInfoImageComponent() {
    SGTheme {
        PostInfoImageComponent(
            model = null,
        )
    }
}