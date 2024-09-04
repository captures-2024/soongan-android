package com.captures2024.soongan.feature.home.ui.gallery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavOptions
import com.captures2024.soongan.core.design.R
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillTopArrow
import com.captures2024.soongan.core.designsystem.theme.PrimaryA
import com.captures2024.soongan.core.designsystem.theme.PrimaryB
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.feature.home.state.HomeGalleryListUIState
import kotlinx.coroutines.launch

@Composable
internal fun HomeGalleryListScreen(
    modifier: Modifier = Modifier,
    uiState: HomeGalleryListUIState,
    onClickBack: () -> Unit = {},
    onClickItem: (String, NavOptions?) -> Unit = { _, _ -> },
) {
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
            HomeGalleryListTopBar(
                onClickBack = onClickBack,
                lazyStaggeredGridState = lazyStaggeredGridState
            )
        }
        items(uiState.photoList, key = { it.id }) {
            when (val item = it) {
                is UserPost.SkeletonPost -> HomeGallerySkeletonItem(item = item)

                is UserPost.PhotoPost -> HomeGalleryImageItem(item = item) { currentItem ->
                    val options = NavOptions.Builder().build()
                    onClickItem(
                        currentItem.id.toString(),
                        options
                    )
                }
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                horizontal = 20.dp,
                vertical = 36.dp
            ),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        HomeGalleryButton(
            modifier = Modifier.offset(
                y = when (lazyStaggeredGridState.firstVisibleItemIndex) {
                    0 -> 100.dp
                    else -> 0.dp
                }
            ),
            onClick = {
                coroutineScope.launch {
                    lazyStaggeredGridState.animateScrollToItem(index = 0)
                }
            }
        ) {
            Icon(
                imageVector = MyIconPack.IconNonFillTopArrow,
                contentDescription = "",
                tint = PrimaryA,
                modifier = Modifier.size(
                    width = 20.dp,
                    height = 16.dp
                )
            )
        }
    }
}

@DevicePreviews
@Composable
private fun HomeGalleryListScreenPreview() {
    HomeGalleryListScreen(
        uiState = HomeGalleryListUIState.Init
    )
}