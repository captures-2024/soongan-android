package com.captures2024.soongan.core.designsystem.component.gallery

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import com.captures2024.soongan.core.design.R
import com.captures2024.soongan.core.designsystem.component.SoonGanIconButton
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillTopArrow
import com.captures2024.soongan.core.designsystem.theme.SGColor
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@Composable
fun SoonGanGallery(
    modifier: Modifier = Modifier,
    lazyStaggeredGridState: LazyStaggeredGridState = rememberLazyStaggeredGridState(),
    hasNextPage: Boolean = false,
    onLoadNextPage: () -> Unit = {},
    content: LazyStaggeredGridScope.() -> Unit
) {
    val shouldLoadMore = remember {
        derivedStateOf {
            val totalItemsCount = lazyStaggeredGridState.layoutInfo.totalItemsCount
            val lastVisibleItemIndex =
                lazyStaggeredGridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            lastVisibleItemIndex >= (totalItemsCount - 2)
        }
    }

    if (hasNextPage) {
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
                contentDescription = "icon that scrolls up to the top",
                tint = SGColor.primaryA,
                modifier = Modifier.size(
                    width = 20.dp,
                    height = 16.dp
                )
            )
        }
    }
}

@Preview
@Composable
private fun SoonGanGalleryPreview() {
    SoonGanGallery {
        item(span = StaggeredGridItemSpan.FullLine) {
            Text("preview test")
        }
        items(3) {
            Image(painter = painterResource(R.drawable.test), contentDescription = null)
        }
    }
}