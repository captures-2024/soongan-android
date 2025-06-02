package com.captures2024.soongan.presentation.feature.main.home.component.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.captures2024.soongan.presentation.designsystem.ui.component.gallery.SGGallery
import com.captures2024.soongan.presentation.designsystem.ui.component.gallery.SGGalleryEmptyItem
import com.captures2024.soongan.presentation.designsystem.ui.component.gallery.SGGalleryErrorItem
import com.captures2024.soongan.presentation.designsystem.ui.component.gallery.SGGalleryImageItem
import com.captures2024.soongan.presentation.designsystem.ui.component.gallery.SGGalleryPaginatingItem
import com.captures2024.soongan.presentation.designsystem.ui.component.gallery.SGGallerySkeletonItem
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.home.R
import com.captures2024.soongan.presentation.feature.main.home.component.gallery.GalleryFilterBottomSheetComponent
import com.captures2024.soongan.presentation.feature.main.home.component.gallery.GalleryTopBarComponent
import com.captures2024.soongan.presentation.viewmodel.main.home.HomeGalleryViewModel
import com.captures2024.soongan.presentation.viewmodel.model.PaginationStatus
import com.captures2024.soongan.presentation.viewmodel.model.PostOrderType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeGalleryScreen(
    state: HomeGalleryViewModel.State,
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
    onClickFilter: () -> Unit,
    onRefresh: () -> Unit,
    onLoadNextPage: () -> Unit,
    onClickPost: (Long) -> Unit,
    onClickRegisterPost: () -> Unit,
    onDismissRequestFilterBottomSheet: () -> Unit,
    onClickFilterItem: (PostOrderType) -> Unit,
) {
    val pullToRefreshState = rememberPullToRefreshState()
    val lazyStaggeredGridState = rememberLazyStaggeredGridState()
    val rememberSkeletonItemHeight = remember { List(16) { (150..300).random() } }

    PullToRefreshBox(
        isRefreshing = state.isRefreshing,
        onRefresh = onRefresh,
        modifier = modifier.fillMaxSize(),
        state = pullToRefreshState,
        indicator = {
            Indicator(
                modifier = Modifier.align(Alignment.TopCenter),
                isRefreshing = state.isRefreshing,
                containerColor = SGColor.Grayscale.white,
                color = SGColor.Grayscale.black100,
                state = pullToRefreshState,
            )
        },
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            SGGallery(
                modifier = Modifier.fillMaxSize(),
                lazyStaggeredGridState = lazyStaggeredGridState,
                isInitPage = state.isInitPage,
                hasNextPage = state.hasNextPage,
                onLoadNextPage = onLoadNextPage,
                header = @Composable {
                    GalleryTopBarComponent(
                        round = state.round?.toString() + stringResource(R.string.home_gallery_round_unit),
                        subject = state.subject,
                        modifier = Modifier.fillMaxWidth(),
                        onClickBack = onClickBack,
                        onClickFilter = onClickFilter,
                    )
                },
            ) {
                when {
                    state.posts.isEmpty() -> when (state.paginationStatus) {
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
                                    emptyText = stringResource(R.string.home_gallery_post_empty_content),
                                    registrationText = stringResource(R.string.home_gallery_post_empty_button),
                                    modifier = modifier.padding(top = 100.dp),
                                    onClickRegistrationText = onClickRegisterPost,
                                )
                            }
                        }

                        PaginationStatus.FAILED -> {
                            item(span = StaggeredGridItemSpan.FullLine) {
                                SGGalleryErrorItem(
                                    errorText = stringResource(R.string.home_gallery_post_error),
                                )
                            }
                        }
                    }

                    else -> items(
                        items = state.posts,
                        key = { it.postId },
                    ) {
                        SGGalleryImageItem(
                            imageUrl = it.imageUrl,
                            onClick = { onClickPost(it.postId) },
                        )
                    }
                }

                if (state.paginationStatus == PaginationStatus.PAGING_LOAD) {
                    item(span = StaggeredGridItemSpan.FullLine) {
                        SGGalleryPaginatingItem()
                    }
                }
            }
        }
    }

    if (state.isShowFilterBottomSheet) {
        GalleryFilterBottomSheetComponent(
            selectedPostOrderType = state.orderType,
            onDismissRequest = onDismissRequestFilterBottomSheet,
            onClickItem = onClickFilterItem,
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewHomeGalleryScreen_Default() {
    SGTheme {
        HomeGalleryScreen(
            state = HomeGalleryViewModel.State(
                isRefreshing = false,
                round = 1,
                subject = "test",
                posts = emptyList(),
                orderType = PostOrderType.MOST_LIKED,
                paginationStatus = PaginationStatus.DEFAULT,
                loadPage = 0,
                loadPageSize = 20,
                hasNextPage = true,
                isShowFilterBottomSheet = false,
            ),
            onClickBack = {},
            onClickFilter = {},
            onRefresh = {},
            onLoadNextPage = {},
            onClickPost = {},
            onClickRegisterPost = {},
            onDismissRequestFilterBottomSheet = {},
            onClickFilterItem = {},
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewHomeGalleryScreen_LoadEmpty() {
    SGTheme {
        HomeGalleryScreen(
            state = HomeGalleryViewModel.State(
                isRefreshing = false,
                round = 1,
                subject = "test",
                posts = emptyList(),
                orderType = PostOrderType.MOST_LIKED,
                paginationStatus = PaginationStatus.SUCCESS,
                loadPage = 0,
                loadPageSize = 20,
                hasNextPage = true,
                isShowFilterBottomSheet = false,
            ),
            onClickBack = {},
            onClickFilter = {},
            onRefresh = {},
            onLoadNextPage = {},
            onClickPost = {},
            onClickRegisterPost = {},
            onDismissRequestFilterBottomSheet = {},
            onClickFilterItem = {},
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewHomeGalleryScreen_FailLoad() {
    SGTheme {
        HomeGalleryScreen(
            state = HomeGalleryViewModel.State(
                isRefreshing = false,
                round = 1,
                subject = "test",
                posts = emptyList(),
                orderType = PostOrderType.MOST_LIKED,
                paginationStatus = PaginationStatus.FAILED,
                loadPage = 0,
                loadPageSize = 20,
                hasNextPage = true,
                isShowFilterBottomSheet = false,
            ),
            onClickBack = {},
            onClickFilter = {},
            onRefresh = {},
            onLoadNextPage = {},
            onClickPost = {},
            onClickRegisterPost = {},
            onDismissRequestFilterBottomSheet = {},
            onClickFilterItem = {},
        )
    }
}
