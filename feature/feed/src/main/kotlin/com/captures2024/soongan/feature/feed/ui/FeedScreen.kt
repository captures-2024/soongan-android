package com.captures2024.soongan.feature.feed.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.captures2024.soongan.core.designsystem.ui.component.gallery.SGGallery
import com.captures2024.soongan.core.designsystem.ui.component.gallery.SGGalleryImageItem
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.core.viewmodel.feed.FeedViewModel
import com.captures2024.soongan.core.viewmodel.model.PaginationStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FeedScreen(
    uiState: FeedViewModel.State,
    modifier: Modifier = Modifier,
    onRefresh: () -> Unit,
    onClickTitle: () -> Unit,
    onClickFilter: () -> Unit,
    onClickPost: (Long) -> Unit,
) {
    val pullToRefreshState = rememberPullToRefreshState()

    PullToRefreshBox(
        isRefreshing = uiState.isRefreshing,
        onRefresh = onRefresh,
        modifier = modifier,
        state = pullToRefreshState,
        indicator = {
            Indicator(
                modifier = Modifier.align(Alignment.TopCenter),
                isRefreshing = uiState.isRefreshing,
                containerColor = SGColor.white,
                color = SGColor.black,
                state = pullToRefreshState,
            )
        },
    ) {
        FeedScreen(
            uiState = uiState,
            onClickTitle = onClickTitle,
            onClickFilter = onClickFilter,
            onClickPost = onClickPost,
        )
    }
}

@Composable
private fun FeedScreen(
    uiState: FeedViewModel.State,
    modifier: Modifier = Modifier,
    onClickTitle: () -> Unit = {},
    onClickFilter: () -> Unit = {},
    onClickPost: (Long) -> Unit = {},
    /* temp SGGallery params */
    isInitPage: Boolean = true,
    paginationStatus: PaginationStatus = PaginationStatus.EXHAUST,
    onLoadNextPage: () -> Unit = {},
) {
    val lazyStaggeredGridState = rememberLazyStaggeredGridState()

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        SGGallery(
            lazyStaggeredGridState = lazyStaggeredGridState,
            isInitPage = isInitPage,
            hasNextPage = (paginationStatus != PaginationStatus.EXHAUST),
            onLoadNextPage = onLoadNextPage,
            header = {
                FeedGalleryHeader(
                    selectedOption = uiState.currentTitleOption,
                    onClickTitle = onClickTitle,
                    onClickFilter = onClickFilter,
                )
            },
        ) {
            items(items = uiState.currentRoundGallery, key = { it.postId }) {
                SGGalleryImageItem(
                    imageUrl = it.imageUrl,
                    onClick = { onClickPost(it.postId) },
                )
            }
        }
    }
}

@DevicePreviews
@Composable
private fun FeedScreenPreview() {
    val uiState = FeedViewModel.State()

    FeedScreen(uiState = uiState)
}
