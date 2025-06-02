package com.captures2024.soongan.feature.home.ui.gallery

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
import com.captures2024.soongan.core.model.dto.GalleryPostDto
import com.captures2024.soongan.core.viewmodel.home.HomeGalleryViewModel
import com.captures2024.soongan.core.viewmodel.model.PaginationStatus
import com.captures2024.soongan.feature.home.R
import com.captures2024.soongan.feature.home.ui.gallery.component.HomeGalleryHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeGalleryScreen(
    uiState: HomeGalleryViewModel.State,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {},
    onClickFilter: () -> Unit = {},
    onRefresh: () -> Unit = {},
    onLoadNextPage: () -> Unit = {},
    onClickPost: (Long) -> Unit = {},
    onClickRegistrationText: () -> Unit = {},
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
        HomeGalleryScreen(
            posts = uiState.posts,
            paginationStatus = uiState.paginationStatus,
            isInitPage = uiState.isFirstPage,
            onBackPressed = onBackPressed,
            onLoadNextPage = onLoadNextPage,
            onClickPost = onClickPost,
            onClickFilter = onClickFilter,
            onClickRegistrationText = onClickRegistrationText,
        )
    }
}

@Composable
private fun HomeGalleryScreen(
    posts: List<GalleryPostDto>,
    paginationStatus: PaginationStatus,
    isInitPage: Boolean,
    onBackPressed: () -> Unit,
    onClickFilter: () -> Unit,
    onLoadNextPage: () -> Unit,
    onClickPost: (Long) -> Unit,
    onClickRegistrationText: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val lazyStaggeredGridState = rememberLazyStaggeredGridState()

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        SGGallery(
            modifier = modifier,
            lazyStaggeredGridState = lazyStaggeredGridState,
            isInitPage = isInitPage,
            hasNextPage = (paginationStatus != PaginationStatus.EXHAUST),
            onLoadNextPage = onLoadNextPage,
            header = {
                HomeGalleryHeader(
                    onBackPressed = onBackPressed,
                    onClickFilter = onClickFilter,
                )
            },
        ) {
            when (paginationStatus) {
                PaginationStatus.LOADING -> items(listOf(258, 192, 275, 268, 275, 192)) { height ->
                    SGGallerySkeletonItem(height = height)
                }

                PaginationStatus.EMPTY -> Unit

                else -> items(items = posts, key = { it.postId }) {
                    SGGalleryImageItem(
                        imageUrl = it.imageUrl,
                        onClick = { onClickPost(it.postId) },
                    )
                }
            }

            if (paginationStatus == PaginationStatus.PAGINATING) {
                item(span = StaggeredGridItemSpan.FullLine) {
                    SGGalleryPaginatingItem()
                }
            }

            if (paginationStatus == PaginationStatus.ERROR && posts.isNotEmpty()) {
                item(span = StaggeredGridItemSpan.FullLine) {
                    SGGalleryErrorItem(errorText = stringResource(R.string.home_gallery_error_text))
                }
            }
        }

        if (paginationStatus == PaginationStatus.EMPTY) {
            SGGalleryEmptyItem(
                emptyText = stringResource(R.string.home_gallery_empty_text),
                registrationText = stringResource(R.string.home_gallery_registration_text),
                modifier = modifier.padding(top = 100.dp), // HomeGalleryTopBar height
                onClickRegistrationText = onClickRegistrationText,
            )
        }

        if (paginationStatus == PaginationStatus.ERROR && posts.isEmpty()) {
            SGGalleryErrorItem(
                errorText = stringResource(R.string.home_gallery_error_text),
                modifier = modifier.padding(top = 100.dp), // HomeGalleryTopBar height
            )
        }
    }
}

@DevicePreviews
@Composable
private fun HomeGalleryScreenPreview() {
    HomeGalleryScreen(
        uiState = HomeGalleryViewModel.State(),
    )
}
