package com.captures2024.soongan.feature.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.design.R
import com.captures2024.soongan.core.designsystem.component.shimmerBrush
import com.captures2024.soongan.core.designsystem.theme.PrimaryB
import com.captures2024.soongan.core.designsystem.theme.dropShadow
import com.captures2024.soongan.core.designsystem.util.DevicePreviews

@Composable
internal fun HomeGalleryScreen(
    modifier: Modifier = Modifier,
) {
    val photos by rememberSaveable { mutableStateOf(List(100) { it }) }
    val lazyGridState = rememberLazyGridState()
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize()
            .background(color = PrimaryB)
            .paint(
                painter = painterResource(id = R.drawable.background_home_gallery),
                contentScale = ContentScale.Crop,
                alpha = 0.8f
            ),
        state = lazyGridState,
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item(span = { GridItemSpan(2) }) {
            HomeGalleryTopBar(
                lazyGridState = lazyGridState,
                onClickBack = { /* TODO */ },
                onClickFilter = { /* TODO */ }
            )
        }
        items(photos, key = { it }) {
            HomeGallerySkeletonItem()
        }
    }
}

@Composable
private fun HomeGallerySkeletonItem(
    modifier: Modifier = Modifier
) = Box(
    modifier = modifier.width(190.dp)
        .height(258.dp)
        .dropShadow(
            shape = RoundedCornerShape(0.dp),
            color = Color(0x40000000),
            offsetY = 4.dp,
            blur = 4.dp
        )
        .background(Color(0xFFD9D9D9))
        .background(brush = shimmerBrush(targetValue = 1300f))
)

@DevicePreviews
@Composable
private fun HomeGalleryScreenPreview() {
    HomeGalleryScreen()
}

@DevicePreviews
@Composable
private fun HomeGallerySkeletonItemPreview() {
    HomeGallerySkeletonItem()
}