package com.captures2024.soongan.presentation.feature.main.post.component.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.captures2024.soongan.core.android.utils.LocalAnalyticsHelper
import com.captures2024.soongan.presentation.designsystem.icon.MyIconPack
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.IconNonFillLeftArrow
import com.captures2024.soongan.presentation.designsystem.ui.component.ZoomableBox
import com.captures2024.soongan.presentation.designsystem.ui.component.button.SGIconCircleButton
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.core.model.AppConst
import com.captures2024.soongan.presentation.feature.main.post.R
import com.captures2024.soongan.presentation.viewmodel.main.post.ImageViewerViewModel
import kotlinx.coroutines.delay

@Composable
internal fun ImageViewerScreen(
    state: ImageViewerViewModel.State,
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
) {
    val analyticsHelper = LocalAnalyticsHelper.current
    val context = LocalContext.current
    var currentTimerValue by remember { mutableLongStateOf(AppConst.Main.Post.IMAGE_DEFAULT_DURATION) }

    val model = ImageRequest.Builder(context)
        .data(state.url)
        .build()

    val imageLoader = ImageLoader(context)

    LaunchedEffect(key1 = currentTimerValue) {
        if (currentTimerValue > 0) {
            delay(1000L)
            currentTimerValue -= 1000L
        }

        val bitmap = imageLoader.execute(model).drawable?.toBitmap()
        analyticsHelper.d { "height = ${bitmap?.height}, width = ${bitmap?.width}" }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(SGColor.black),
        contentAlignment = Alignment.TopStart,
    ) {
        ZoomableBox(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {
                        if (currentTimerValue > 0L) {
                            currentTimerValue -= 1000L
                        }
                    },
                ),
        ) {
            AsyncImage(
                model = model,
                contentDescription = stringResource(R.string.image_description),
                modifier = Modifier
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale,
                        translationX = offsetX,
                        translationY = offsetY,
                    ),
                contentScale = ContentScale.FillWidth,
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .height(80.dp),
            contentAlignment = Alignment.CenterStart,
        ) {
            if (currentTimerValue <= 0) {
                SGIconCircleButton(
                    imageVector = MyIconPack.IconNonFillLeftArrow,
                    contentDescription = stringResource(R.string.back_description),
                    iconWidth = 20.dp,
                    iconHeight = 16.dp,
                    onClick = onClickBack,
                )
            }
        }
    }
}

@DevicePreviews
@Composable
private fun PreviewImageViewerScreen() {
    SGTheme {
        ImageViewerScreen(
            state = ImageViewerViewModel.State(
                url = "",
            ),
            onClickBack = {},
        )
    }
}
