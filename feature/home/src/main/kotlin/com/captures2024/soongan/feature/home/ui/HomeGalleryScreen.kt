package com.captures2024.soongan.feature.home.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.design.R
import com.captures2024.soongan.core.designsystem.component.shimmerBrush
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconBack2
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFilter
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconTopArrow
import com.captures2024.soongan.core.designsystem.theme.PrimaryA
import com.captures2024.soongan.core.designsystem.theme.PrimaryB
import com.captures2024.soongan.core.designsystem.theme.dropShadow
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.model.GridItem
import kotlinx.coroutines.launch

private val samplePhotos = List(100) {
    GridItem(
        id = it.toString(),
        size = (100 .. 300).random().dp
    )
}

@Composable
internal fun HomeGalleryScreen(
    modifier: Modifier = Modifier,
) {
    val photos by rememberSaveable { mutableStateOf(samplePhotos) }
    val lazyStaggeredGridState = rememberLazyStaggeredGridState()
    val coroutineScope = rememberCoroutineScope()

    LazyVerticalStaggeredGrid(
        modifier = modifier
            .fillMaxSize()
            .background(color = PrimaryB)
            .paint(
                painter = painterResource(id = R.drawable.background_home_gallery),
                contentScale = ContentScale.Crop,
                alpha = 0.8f
            ),
        state = lazyStaggeredGridState,
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalItemSpacing = 12.dp,
        contentPadding = PaddingValues(8.dp)
    ) {
        item(span = StaggeredGridItemSpan.FullLine) {
            HomeGalleryTopBar(lazyStaggeredGridState = lazyStaggeredGridState)
        }
        items(photos, key = { it.id }) {
            HomeGallerySkeletonItem(item = it)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                horizontal = 20.dp,
                vertical = 36.dp
            ),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.End
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(100.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            HomeGalleryButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = MyIconPack.IconBack2,
                    contentDescription = "",
                    tint = PrimaryA
                )
            }
            HomeGalleryButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = MyIconPack.IconFilter,
                    contentDescription = "",
                    tint = PrimaryA
                )
            }

        }
        HomeGalleryButton(
            modifier = Modifier.offset(
                y = when (lazyStaggeredGridState.firstVisibleItemIndex) {
                    0 -> 100.dp
                    else -> 0.dp
                }
            ),
            onClick = {
                coroutineScope.launch {
                    lazyStaggeredGridState.animateScrollToItem(index = 1)
                }
            }
        ) {
            Icon(
                imageVector = MyIconPack.IconTopArrow,
                contentDescription = "",
                tint = PrimaryA
            )
        }
    }

    

}

@Composable
private fun HomeGallerySkeletonItem(
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

@DevicePreviews
@Composable
private fun HomeGalleryScreenPreview() {
    HomeGalleryScreen()
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