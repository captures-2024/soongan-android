package com.captures2024.soongan.presentation.feature.main.profile.component.notification

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.core.model.utils.NotificationType
import com.captures2024.soongan.core.model.utils.NotificationsCountTable
import com.captures2024.soongan.presentation.feature.main.profile.utils.extension.getTabResId
import com.captures2024.soongan.presentation.feature.main.profile.utils.nonScaleAnnotatedTitle
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NotificationTapRowComponent(
    tabs: List<NotificationType>,
    selectedCategory: NotificationType,
    notificationsCountTable: NotificationsCountTable,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    onClickCategory: (NotificationType) -> Unit,
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
        containerColor = SGColor.Grayscale.white,
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
                NotificationTabComponent(
                    annotatedString = nonScaleAnnotatedTitle(
                        title = stringResource(tab.getTabResId()),
                        count = notificationsCountTable[tab] ?: 0,
                    ),
                    selected = (tabIndex == index),
                    onClick = { onClickCategory(tab) },
                )
            }
        },
    )
}

@DevicePreviews
@Composable
private fun PreviewNotificationTapRowComponent() {
    val pagerState = rememberPagerState(
        pageCount = { 3 },
        initialPageOffsetFraction = 0f,
        initialPage = 0,
    )

    SGTheme {
        NotificationTapRowComponent(
            tabs = NotificationType.entries,
            selectedCategory = NotificationType.CONTEST,
            notificationsCountTable = emptyMap(),
            pagerState = pagerState,
            onClickCategory = {},
        )
    }
}
