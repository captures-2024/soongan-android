package com.captures2024.soongan.presentation.designsystem.ui.component.gallery

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.presentation.designsystem.icon.MyIconPack
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.IconNonFillTopArrow
import com.captures2024.soongan.presentation.designsystem.ui.R
import com.captures2024.soongan.presentation.designsystem.ui.component.button.SGIconCircleButton
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@Composable
fun SGGallery(
    modifier: Modifier = Modifier,
    lazyStaggeredGridState: LazyStaggeredGridState = rememberLazyStaggeredGridState(),
    isInitPage: Boolean = true,
    hasNextPage: Boolean = false,
    onLoadNextPage: () -> Unit = {},
    header: @Composable (() -> Unit)? = null,
    content: LazyStaggeredGridScope.() -> Unit,
) {
    val shouldLoadMore = remember {
        derivedStateOf {
            val totalItemsCount = lazyStaggeredGridState.layoutInfo.totalItemsCount
            val lastVisibleItemIndex =
                lazyStaggeredGridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            lastVisibleItemIndex >= (totalItemsCount - 2)
        }
    }

    if (!isInitPage && hasNextPage) {
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
                contentScale = ContentScale.FillBounds,
                alpha = 0.8f,
            ),
        state = lazyStaggeredGridState,
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalItemSpacing = 12.dp,
        contentPadding = PaddingValues(8.dp),
    ) {
        header?.let {
            item(span = StaggeredGridItemSpan.FullLine) {
                header()
            }
        }

        content()
    }

    ScrollToTopIcon(lazyStaggeredGridState = lazyStaggeredGridState)
}

@Composable
private fun ScrollToTopIcon(
    lazyStaggeredGridState: LazyStaggeredGridState,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                horizontal = 20.dp,
                vertical = 36.dp,
            ),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End,
    ) {
        SGIconCircleButton(
            imageVector = MyIconPack.IconNonFillTopArrow,
            contentDescription = "scroll up to the top",
            modifier = Modifier.offset(
                y = when (lazyStaggeredGridState.firstVisibleItemIndex) {
                    0 -> 100.dp
                    else -> 0.dp
                },
            ),
            iconWidth = 20.dp,
            iconHeight = 16.dp,
            onClick = {
                coroutineScope.launch {
                    lazyStaggeredGridState.animateScrollToItem(index = 0)
                }
            },
        )
    }
}

@Preview
@Composable
private fun SGGalleryPreview() {
    SGGallery(
        header = {
            SGGalleryHeader {
                SGGalleryHeaderTitle(
                    prefix = "1회차",
                    suffix = "평화",
                )
            }
        },
    ) {
        items(3) {
            Image(painter = painterResource(R.drawable.test), contentDescription = null)
        }
    }
}
