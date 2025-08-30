package com.captures2024.soongan.presentation.feature.main.profile.component.faq

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.viewmodel.model.FaqCategory
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FaqTapRowComponent(
    tabs: List<FaqCategory>,
    selectedCategory: FaqCategory,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    onClickCategory: (FaqCategory) -> Unit,
) {
    val scope = rememberCoroutineScope()

    val tabIndex = tabs.indexOf(selectedCategory)

    LaunchedEffect(selectedCategory) {
        scope.launch {
            pagerState.animateScrollToPage(tabIndex)
        }
    }

    SecondaryTabRow(
        selectedTabIndex = tabIndex,
        modifier = modifier,
        containerColor = SGColor.BG.background,
        indicator = @Composable {
            TabRowDefaults.SecondaryIndicator(
                Modifier
                    .tabIndicatorOffset(
                        selectedTabIndex = tabIndex,
                        matchContentSize = false,
                    )
                    .padding(horizontal = 10.dp),
                height = 2.dp,
                color = SGColor.Grayscale.black100,
            )
        },
        divider = @Composable {
            HorizontalDivider(
                modifier = Modifier.graphicsLayer(alpha = 0.4f),
                thickness = 1.dp,
                color = SGColor.Grayscale.black100.copy(alpha = 0.3f),
            )
        },
        tabs = @Composable {
            tabs.forEachIndexed { index, tab ->
                FaqTabComponent(
                    category = tab,
                    selected = (tabIndex == index),
                    onClick = { onClickCategory(tab) },
                )
            }
        },
    )
}

@DevicePreviews
@Composable
private fun PreviewFaqTapRowComponent() {
    val pagerState = rememberPagerState(
        pageCount = { 3 },
        initialPageOffsetFraction = 0f,
        initialPage = 0,
    )

    SGTheme {
        FaqTapRowComponent(
            tabs = FaqCategory.entries,
            selectedCategory = FaqCategory.DEFAULT,
            pagerState = pagerState,
            onClickCategory = {},
        )
    }
}
