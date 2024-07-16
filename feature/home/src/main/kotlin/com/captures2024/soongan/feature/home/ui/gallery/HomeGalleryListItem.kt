package com.captures2024.soongan.feature.home.ui.gallery

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.captures2024.soongan.core.designsystem.component.shimmerBrush
import com.captures2024.soongan.core.designsystem.theme.dropShadow
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.model.mock.GalleryImage
import com.captures2024.soongan.core.model.mock.GridItem
import timber.log.Timber

@Composable
internal fun HomeGallerySkeletonItem(
    modifier: Modifier = Modifier,
    item: GridItem
) = Box(
    modifier = modifier
        .width(190.dp)
        .height(item.size)
        .dropShadow(
            shape = RoundedCornerShape(0.dp),
            color = Color(0x40000000),
            offsetY = 4.dp,
            blur = 4.dp
        )
        .background(Color(0xFFD9D9D9))
        .background(brush = shimmerBrush(targetValue = 1300f))
)


@Composable
internal fun HomeGalleryImageItem(
    modifier: Modifier = Modifier,
    item: GalleryImage,
    onClick: (GalleryImage) -> Unit = {},
) {
    val showShimmer = remember { mutableStateOf(true) }

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(item.url)
            .build(),
        contentDescription = item.title,
        modifier = modifier
            .background(shimmerBrush(targetValue = 1300f, showShimmer = showShimmer.value))
            .width(190.dp)
            .heightIn(min = 100.dp)
            .dropShadow(
                shape = RoundedCornerShape(0.dp),
                color = Color(0x40000000),
                offsetY = 4.dp,
                blur = 4.dp
            )
            .clickable {
                Timber.tag("HomeGalleryImageItem").d("showShimmer = ${showShimmer.value}")
                if (!showShimmer.value) {
                    onClick(item)
                }
            },
        onSuccess = { showShimmer.value = false },
        contentScale = ContentScale.FillWidth,
    )
}

@DevicePreviews
@Composable
private fun HomeGallerySkeletonItemPreview() {
    HomeGallerySkeletonItem(
        item = GridItem(
            id = "",
            size = (100..200).random().dp
        )
    )
}

@DevicePreviews
@Composable
private fun HomeGalleryImageItemPreview() {
    HomeGalleryImageItem(
        item = GalleryImage(
            id = 0,
            url = "",
            title = ""
        )
    )
}