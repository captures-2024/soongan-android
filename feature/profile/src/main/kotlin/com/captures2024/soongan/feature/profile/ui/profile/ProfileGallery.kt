package com.captures2024.soongan.feature.profile.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.captures2024.soongan.core.design.R
import com.captures2024.soongan.core.designsystem.component.shimmerBrush
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.theme.dropShadow
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.core.model.mock.samplePhotos

@Composable
internal fun ProfileGallery(
    userPhotos: List<UserPost.PhotoPost>,
    modifier: Modifier = Modifier,
    onClickUserPhoto: (UserPost.PhotoPost) -> Unit = {},
) {
    val lazyStaggeredGridState = rememberLazyStaggeredGridState()
    val showShimmer = remember { mutableStateOf(true) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = SGColor.primaryB)
    ) {
        Image(
            painter = painterResource(id = R.drawable.background_home_gallery),
            contentDescription = "background image",
            contentScale = ContentScale.Crop,
            alpha = 0.8f
        )

        LazyVerticalStaggeredGrid(
            modifier = Modifier.fillMaxSize(),
            state = lazyStaggeredGridState,
            columns = StaggeredGridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalItemSpacing = 12.dp,
            contentPadding = PaddingValues(8.dp)
        ) {
            items(
                items = userPhotos,
                key = { it.id }
            ) {
                AsyncImage(
                    model = it.url,
                    contentDescription = it.title,
                    modifier = modifier
                        .background(
                            shimmerBrush(
                                targetValue = 1300f,
                                showShimmer = showShimmer.value
                            )
                        )
                        .heightIn(100.dp)
                        .width(190.dp)
                        .dropShadow(
                            shape = RoundedCornerShape(0.dp),
                            color = Color(0x40000000),
                            offsetY = 4.dp,
                            blur = 4.dp
                        )
                        .clickable(enabled = !showShimmer.value) {
                            onClickUserPhoto(it)
                        },
                    error = painterResource(R.drawable.test),
                    onSuccess = { showShimmer.value = false },
                    contentScale = ContentScale.FillWidth,
                )
            }
        }
    }
}

@DevicePreviews
@Composable
private fun ProfileGalleryPreview() {
    val samples = samplePhotos.map { it as UserPost.PhotoPost }

    ProfileGallery(userPhotos = samples)
}