package com.captures2024.soongan.feature.home.ui.gallery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.design.R
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.component.SoonGanIconButton
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillTopArrow
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.model.dto.GalleryPostDto
import com.captures2024.soongan.feature.home.state.home_gallery.HomeGalleryUIState
import com.captures2024.soongan.feature.home.ui.gallery.component.HomeGalleryImageItem
import com.captures2024.soongan.feature.home.ui.gallery.component.HomeGallerySkeletonItem
import com.captures2024.soongan.feature.home.ui.gallery.component.HomeGalleryTopBar
import com.captures2024.soongan.feature.home.utils.PaginationStatus
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeGalleryScreen(
    uiState: HomeGalleryUIState,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {},
    onRefresh: () -> Unit = {},
    onLoadNextPage: () -> Unit = {},
    onClickPost: (GalleryPostDto) -> Unit = {},
    onClickFilter: () -> Unit = {},
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
                state = pullToRefreshState
            )
        }
    ) {
        HomeGalleryScreen(
            posts = uiState.posts,
            paginationStatus = uiState.paginationStatus,
            onBackPressed = onBackPressed,
            onLoadNextPage = onLoadNextPage,
            onClickPost = onClickPost,
            onClickFilter = onClickFilter,
        )
    }
}

@Composable
private fun HomeGalleryScreen(
    posts: List<GalleryPostDto>,
    paginationStatus: PaginationStatus,
    onBackPressed: () -> Unit,
    onLoadNextPage: () -> Unit,
    onClickPost: (GalleryPostDto) -> Unit,
    onClickFilter: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val lazyStaggeredGridState = rememberLazyStaggeredGridState()
    val coroutineScope = rememberCoroutineScope()

    val shouldLoadMore = remember {
        derivedStateOf {
            val totalItemsCount = lazyStaggeredGridState.layoutInfo.totalItemsCount
            val lastVisibleItemIndex =
                lazyStaggeredGridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            lastVisibleItemIndex >= (totalItemsCount - 2)
        }
    }

    if (paginationStatus != PaginationStatus.EXHAUST) {
        LaunchedEffect(lazyStaggeredGridState) {
            snapshotFlow { shouldLoadMore.value }
                .distinctUntilChanged()
                .filter { it }
                .collect {
                    onLoadNextPage()
                }
        }
    }

    LazyVerticalStaggeredGrid(
        modifier = modifier
            .fillMaxSize()
            .background(color = SGColor.primaryB)
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
            HomeGalleryTopBar(
                onBackPressed = onBackPressed,
                onClickFilter = onClickFilter,
            )
        }
        when (paginationStatus) {
            PaginationStatus.LOADING -> {
                items(listOf(258, 192, 275, 268, 275, 192)) { height ->
                    HomeGallerySkeletonItem(height = height)
                }
            }

            PaginationStatus.EMPTY -> {
                item(span = StaggeredGridItemSpan.FullLine) {
                    Box(modifier = Modifier.height(100.dp), contentAlignment = Alignment.Center) {
                        NonScaleText(
                            text = "게시글이 없어요.",
                            fontSize = 20.sp
                        )
                    }
                }
            }

            else -> {
                items(
                    items = posts,
                    key = { it.postId }
                ) {
                    HomeGalleryImageItem(
                        item = it,
                        onClick = onClickPost
                    )
                }
            }
        }

        if (paginationStatus == PaginationStatus.PAGINATING) {
            item(span = StaggeredGridItemSpan.FullLine) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(50.dp),
                        color = SGColor.black
                    )
                }
            }
        }

        if (paginationStatus == PaginationStatus.ERROR) {
            item(span = StaggeredGridItemSpan.FullLine) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    NonScaleText(
                        "게시글을 불러올 수 없어요.",
                        fontSize = 20.sp
                    )
//                    SoonGanIconButton(onClick = {
//                        onRefresh() || onLoadNextPage()
//                    }) {
//                        Icon(
//                            imageVector = ,
//                            contentDescription = "refresh gallery",
//                            modifier = Modifier.size(50.dp),
//                            tint = SGColor.black
//                        )
//                    }

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
        SoonGanIconButton(
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
                tint = SGColor.primaryA,
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
private fun HomeGalleryScreenPreview() {
    HomeGalleryScreen(
        uiState = HomeGalleryUIState()
    )
}