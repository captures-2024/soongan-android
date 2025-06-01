package com.captures2024.soongan.presentation.feature.main.feed.component.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.designsystem.ui.component.gallery.SGGallery
import com.captures2024.soongan.core.designsystem.ui.component.gallery.SGGalleryEmptyItem
import com.captures2024.soongan.core.designsystem.ui.component.gallery.SGGalleryErrorItem
import com.captures2024.soongan.core.designsystem.ui.component.gallery.SGGalleryImageItem
import com.captures2024.soongan.core.designsystem.ui.component.gallery.SGGalleryPaginatingItem
import com.captures2024.soongan.core.designsystem.ui.component.gallery.SGGallerySkeletonItem
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.feed.R
import com.captures2024.soongan.presentation.feature.main.feed.component.feed.FeedFilterBottomSheetComponent
import com.captures2024.soongan.presentation.feature.main.feed.component.feed.FeedGalleryHeaderComponent
import com.captures2024.soongan.presentation.feature.main.feed.component.feed.FeedTitlePickerBottomSheetComponent
import com.captures2024.soongan.presentation.viewmodel.main.feed.FeedViewModel
import com.captures2024.soongan.presentation.viewmodel.model.PaginationStatus
import com.captures2024.soongan.presentation.viewmodel.model.PostOrderType
import com.captures2024.soongan.presentation.viewmodel.model.feed.TitleOption

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FeedScreen(
    uiState: FeedViewModel.State,
    modifier: Modifier = Modifier,
    onRefresh: () -> Unit,
    onLoadNextPage: () -> Unit,
    onClickTitle: () -> Unit,
    onSelectTitleOption: (round: Int) -> Unit,
    onClickFilter: () -> Unit,
    onClickFilterItem: (PostOrderType) -> Unit,
    onClickPost: (Long) -> Unit,
    onTitlePickerDismissRequest: () -> Unit,
    onFilterDismissRequest: () -> Unit,
) {
    val feedState = uiState.feedState

    val pullToRefreshState = rememberPullToRefreshState()
    val lazyStaggeredGridState = rememberLazyStaggeredGridState()
    val rememberSkeletonItemHeight = remember { List(16) { (150..300).random() } }

    PullToRefreshBox(
        isRefreshing = feedState.isRefreshing,
        onRefresh = onRefresh,
        modifier = modifier,
        state = pullToRefreshState,
        indicator = {
            Indicator(
                modifier = Modifier.align(Alignment.TopCenter),
                isRefreshing = feedState.isRefreshing,
                containerColor = SGColor.white,
                color = SGColor.black,
                state = pullToRefreshState,
            )
        },
    ) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            SGGallery(
                lazyStaggeredGridState = lazyStaggeredGridState,
                isInitPage = feedState.isInitPage,
                hasNextPage = feedState.hasNextPage,
                onLoadNextPage = onLoadNextPage,
                header = @Composable {
                    FeedGalleryHeaderComponent(
                        selectedOption = feedState.currentTitleOption,
                        onClickTitle = onClickTitle,
                        onClickFilter = onClickFilter,
                    )
                },
            ) {
                when {
                    feedState.currentRoundGallery.isEmpty() -> when (feedState.paginationStatus) {
                        PaginationStatus.DEFAULT,
                        PaginationStatus.REFRESH_LOAD,
                        PaginationStatus.PAGING_LOAD,
                        -> items(
                            items = rememberSkeletonItemHeight,
                        ) { height ->
                            SGGallerySkeletonItem(height = height)
                        }

                        PaginationStatus.SUCCESS -> {
                            item(span = StaggeredGridItemSpan.FullLine) {
                                SGGalleryEmptyItem(
                                    emptyText = stringResource(R.string.feed_gallery_post_empty_content),
                                    modifier = modifier.padding(top = 100.dp),
                                )
                            }
                        }

                        PaginationStatus.FAILED -> {
                            item(span = StaggeredGridItemSpan.FullLine) {
                                SGGalleryErrorItem(
                                    errorText = stringResource(R.string.feed_gallery_post_error_content),
                                )
                            }
                        }
                    }

                    else -> items(
                        items = feedState.currentRoundGallery,
                        key = { it.postId },
                    ) {
                        SGGalleryImageItem(
                            imageUrl = it.imageUrl,
                            onClick = { onClickPost(it.postId) },
                        )
                    }
                }

                if (feedState.paginationStatus == PaginationStatus.PAGING_LOAD) {
                    item(span = StaggeredGridItemSpan.FullLine) {
                        SGGalleryPaginatingItem()
                    }
                }
            }
        }
    }

    if (uiState.isOpenTitlePickerBottomSheet) {
        FeedTitlePickerBottomSheetComponent(
            selectedOption = feedState.currentTitleOption,
            options = feedState.titleOptions,
            onSelectOption = onSelectTitleOption,
            onDismissRequest = onTitlePickerDismissRequest,
        )
    }

    if (uiState.isOpenFilterBottomSheet) {
        FeedFilterBottomSheetComponent(
            orderType = feedState.postOrderType,
            onClickItem = onClickFilterItem,
            onDismissRequest = onFilterDismissRequest,
        )
    }
}

@DevicePreviews
@Composable
private fun FeedScreenPreview() {
    val uiState = FeedViewModel.State(
        feedState = FeedViewModel.State.FeedState(
            isRefreshing = false,
            postOrderType = PostOrderType.MOST_LIKED,
            paginationStatus = PaginationStatus.DEFAULT,
            titleOptions = listOf(TitleOption()),
            currentRound = 1,
            loadPage = 0,
            hasNextPage = true,
            feed = emptyMap(),
        ),
        isOpenTitlePickerBottomSheet = false,
        isOpenFilterBottomSheet = false,
    )

    FeedScreen(
        uiState = uiState,
        onRefresh = {},
        onLoadNextPage = {},
        onClickTitle = {},
        onSelectTitleOption = {},
        onClickFilter = {},
        onClickFilterItem = {},
        onClickPost = {},
        onTitlePickerDismissRequest = {},
        onFilterDismissRequest = {},
    )
}
