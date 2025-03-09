package com.captures2024.soongan.feature.profile.ui.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import com.captures2024.soongan.core.designsystem.ui.component.gallery.SGGallery
import com.captures2024.soongan.core.designsystem.ui.component.gallery.SGGalleryEmptyItem
import com.captures2024.soongan.core.designsystem.ui.component.gallery.SGGalleryErrorItem
import com.captures2024.soongan.core.designsystem.ui.component.gallery.SGGalleryImageItem
import com.captures2024.soongan.core.designsystem.ui.component.gallery.SGGalleryPaginatingItem
import com.captures2024.soongan.core.designsystem.ui.component.gallery.SGGallerySkeletonItem
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.core.model.dto.GalleryPostDto
import com.captures2024.soongan.core.viewmodel.model.PaginationStatus
import com.captures2024.soongan.feature.profile.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ProfileScreenBody(
    myPosts: List<GalleryPostDto>,
    paginationStatus: PaginationStatus,
    isRefreshing: Boolean,
    modifier: Modifier = Modifier,
    onRefresh: () -> Unit = {},
    onLoadNextPage: () -> Unit = {},
    onClickPhoto: (Long) -> Unit = {},
    onClickRegistrationText: () -> Unit = {},
) {
    val pullToRefreshState = rememberPullToRefreshState()

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        modifier = modifier,
        state = pullToRefreshState,
        indicator = {
            Indicator(
                modifier = Modifier.align(Alignment.TopCenter),
                isRefreshing = isRefreshing,
                containerColor = SGColor.white,
                color = SGColor.black,
                state = pullToRefreshState
            )
        }
    ) {
        ProfileScreenBody(
            myPosts = myPosts,
            paginationStatus = paginationStatus,
            onLoadNextPage = onLoadNextPage,
            onClickPhoto = onClickPhoto,
            onClickRegistrationText = onClickRegistrationText
        )
    }
}

@Composable
private fun ProfileScreenBody(
    myPosts: List<GalleryPostDto>,
    paginationStatus: PaginationStatus,
    onLoadNextPage: () -> Unit,
    onClickPhoto: (Long) -> Unit,
    onClickRegistrationText: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val lazyStaggeredGridState = rememberLazyStaggeredGridState()

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        SGGallery(
            lazyStaggeredGridState = lazyStaggeredGridState,
            hasNextPage = (paginationStatus != PaginationStatus.EXHAUST),
            onLoadNextPage = onLoadNextPage
        ) {
            when (paginationStatus) {
                PaginationStatus.LOADING -> items(listOf(258, 192, 275, 268, 275, 192)) { height ->
                    SGGallerySkeletonItem(height = height)
                }

                PaginationStatus.EMPTY -> Unit

                else -> items(items = myPosts, key = { it.postId }) {
                    SGGalleryImageItem(
                        imageUrl = it.imageUrl,
                        onClick = { onClickPhoto(it.postId) }
                    )
                }
            }

            if (paginationStatus == PaginationStatus.PAGINATING) {
                item(span = StaggeredGridItemSpan.FullLine) {
                    SGGalleryPaginatingItem()
                }
            }

            if (paginationStatus == PaginationStatus.ERROR && myPosts.isNotEmpty()) {
                item(span = StaggeredGridItemSpan.FullLine) {
                    SGGalleryErrorItem(errorText = stringResource(R.string.profile_gallery_error_text))
                }
            }
        }

        if (paginationStatus == PaginationStatus.EMPTY) {
            SGGalleryEmptyItem(
                emptyText = stringResource(R.string.profile_gallery_empty_text),
                registrationText = stringResource(R.string.profile_gallery_registration_text),
                onClickRegistrationText = onClickRegistrationText
            )
        }

        if (paginationStatus == PaginationStatus.ERROR && myPosts.isEmpty()) {
            SGGalleryErrorItem(errorText = stringResource(R.string.profile_gallery_error_text))
        }
    }
}

@DevicePreviews
@Composable
private fun ProfileScreenBodyPreview() {
    ProfileScreenBody(myPosts = emptyList(), paginationStatus = PaginationStatus.EMPTY, isRefreshing = false)
}