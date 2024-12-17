package com.captures2024.soongan.feature.profile.ui.notification

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.designsystem.theme.PrimaryA
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.model.UserNotification
import com.captures2024.soongan.core.model.mock.mockNotifications
import com.captures2024.soongan.core.model.utils.NotificationType
import com.captures2024.soongan.feature.profile.utils.nonScaleAnnotatedTitle
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NotificationBody(
    modifier: Modifier = Modifier,
    notifications: List<UserNotification>,
) {
    val tabs = listOf(
        nonScaleAnnotatedTitle(title = "대회 알림", count = 93),
        nonScaleAnnotatedTitle(title = "활동 알림", count = 8),
        nonScaleAnnotatedTitle(title = "공지 알림", count = 8),
    )
    val pagerState = rememberPagerState(
        pageCount = { tabs.size },
        initialPageOffsetFraction = 0f,
        initialPage = 0,
    )
    val tabIndex = pagerState.currentPage
    val scope = rememberCoroutineScope()

    Column {
        SecondaryTabRow(
            selectedTabIndex = tabIndex,
            modifier = modifier,
            containerColor = Color.White,
            indicator = @Composable {
                TabRowDefaults.SecondaryIndicator(
                    Modifier
                        .tabIndicatorOffset(tabIndex, matchContentSize = false)
                        .padding(horizontal = 10.dp),
                    height = 2.dp,
                    color = Color.Black
                )
            },
            divider = @Composable {
                HorizontalDivider(
                    modifier = Modifier.graphicsLayer(alpha = 0.4f),
                    thickness = 1.dp,
                    color = PrimaryA.copy(alpha = 0.3f)
                )
            },
            tabs = @Composable {
                tabs.forEachIndexed { index, tab ->
                    Tab(
                        modifier = if (tabIndex == index) Modifier else Modifier.graphicsLayer(alpha = 0.4f),
                        selected = tabIndex == index,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = { Text(tab) }
                    )
                }
            }
        )

        HorizontalPager(
            state = pagerState,
            pageContent = @Composable {
                when (tabIndex) {
                    /* 알림 api 구현 이후, notifications filtering 고려 */
                    // 대회 알림
                    0 -> NotificationHistory(notifications = notifications.filter { it.type == NotificationType.CONTEST })

                    // 활동 알림 + 소명 알림
                    1 -> NotificationHistory(notifications = notifications.filter { it.type == NotificationType.ACTION || it.type == NotificationType.VINDICATION })

                    // 공지 알림
                    2 -> NotificationHistory(notifications = notifications.filter { it.type == NotificationType.ANNOUNCEMENT })
                }
            }
        )
    }
}

@DevicePreviews
@Composable
private fun NotificationBodyPreview() {
    NotificationBody(notifications = mockNotifications)
}
