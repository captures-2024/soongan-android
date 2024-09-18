package com.captures2024.soongan.feature.home.ui.postphoto

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.captures2024.soongan.core.designsystem.component.ZoomableBox
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillLeftArrow
import com.captures2024.soongan.core.designsystem.theme.PrimaryA
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.feature.home.state.postphoto.HomePostPhotoUIState
import com.captures2024.soongan.feature.home.ui.component.HomeGalleryButton
import kotlinx.coroutines.delay

private const val DEFAULT_DURATION: Long = 1L * 1L * 1000L

@Composable
internal fun HomePostPhotoScreen(
    modifier: Modifier = Modifier,
    uiState: HomePostPhotoUIState,
    onBackPressed: () -> Unit = {}
) {
    val context = LocalContext.current
    var currentTimerValue by remember(DEFAULT_DURATION) { mutableLongStateOf(DEFAULT_DURATION) }

    val model = ImageRequest.Builder(context)
        .data(uiState.url)
        .build()

    val imageLoader = ImageLoader(context)

    LaunchedEffect(key1 = currentTimerValue) {
        if (currentTimerValue > 0) {
            delay(1000L)
            currentTimerValue -= 1000L
        }

        val bitmap = imageLoader.execute(model).drawable?.toBitmap()
//        Timber.tag("HomePostPhotoScreen").d("height = ${bitmap?.height}, width = ${bitmap?.width}")
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.TopStart
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
                    }
                ),
        ) {
            AsyncImage(
                model = model,
                contentDescription = "image",
                modifier = Modifier
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale,
                        translationX = offsetX,
                        translationY = offsetY
                    ),
                contentScale = ContentScale.FillWidth,
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .height(80.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            if (currentTimerValue <= 0) {
                HomeGalleryButton(onClick = onBackPressed) {
                    Icon(
                        imageVector = MyIconPack.IconNonFillLeftArrow,
                        contentDescription = "back",
                        tint = PrimaryA,
                        modifier = Modifier.size(
                            width = 20.dp,
                            height = 16.dp
                        )
                    )
                }
            }
        }
    }
}

@DevicePreviews
@Composable
private fun HomePostPhotoScreenPreview() {
    HomePostPhotoScreen(
        uiState = HomePostPhotoUIState(
            url = ""
        )
    )
}