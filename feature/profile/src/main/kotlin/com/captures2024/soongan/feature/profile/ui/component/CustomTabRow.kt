package com.captures2024.soongan.feature.profile.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.ui.theme.NanumSquareNeoFontFamily
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTabRow(
    tabs: List<Any>,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
) {
    val tabIndex = pagerState.currentPage
    val scope = rememberCoroutineScope()

    SecondaryTabRow(
        selectedTabIndex = tabIndex,
        modifier = modifier,
        containerColor = SGColor.white,
        indicator = @Composable {
            TabRowDefaults.SecondaryIndicator(
                Modifier
                    .tabIndicatorOffset(tabIndex, matchContentSize = false)
                    .padding(horizontal = 10.dp),
                height = 2.dp,
                color = SGColor.black,
            )
        },
        divider = @Composable {
            HorizontalDivider(
                modifier = Modifier.graphicsLayer(alpha = 0.4f),
                thickness = 1.dp,
                color = SGColor.primaryA.copy(alpha = 0.3f),
            )
        },
        tabs = @Composable {
            tabs.forEachIndexed { index, tab ->
                CustomTab(
                    tab = tab,
                    selected = (tabIndex == index),
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                )
            }
        },
    )
}

@Composable
private fun CustomTab(
    tab: Any,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Tab(
        modifier = if (selected) Modifier else Modifier.graphicsLayer(alpha = 0.4f),
        selected = selected,
        onClick = onClick,
        text = @Composable {
            when (tab) {
                is AnnotatedString -> {
                    SGText(annotatedString = tab)
                }

                is String -> {
                    SGText(
                        text = tab,
                        style = getSGNonScaleTextStyle(
                            color = SGColor.primaryA,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontFamily = NanumSquareNeoFontFamily,
                            letterSpacing = (-5).em,
                        ),
                    )
                }
            }
        },
    )
}
