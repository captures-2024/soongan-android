package com.captures2024.soongan.feature.profile.ui.notification

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.core.model.UserNotification
import com.captures2024.soongan.core.model.mock.mockNotifications
import com.captures2024.soongan.core.model.utils.NotificationType
import com.captures2024.soongan.feature.profile.R
import com.captures2024.soongan.feature.profile.ui.component.CustomTabRow
import com.captures2024.soongan.feature.profile.utils.nonScaleAnnotatedTitle

@Composable
internal fun NotificationBody(
    modifier: Modifier = Modifier,
    notifications: List<UserNotification>,
) {
    val tabs = listOf(
        nonScaleAnnotatedTitle(
            title = stringResource(R.string.contest_notification_title),
            count = 93
        ),
        nonScaleAnnotatedTitle(
            title = stringResource(R.string.user_action_notification_title),
            count = 8
        ),
        nonScaleAnnotatedTitle(
            title = stringResource(R.string.announce_notification_title),
            count = 8
        ),
    )

    val pagerState = rememberPagerState(
        pageCount = { tabs.size },
        initialPageOffsetFraction = 0f,
        initialPage = 0,
    )

    Column {
        CustomTabRow(
            tabs = tabs,
            pagerState = pagerState,
            modifier = modifier,
        )
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false,
        ) { page ->
            when (page) {
                /* 알림 api 구현 이후, notifications filtering 고려 */
                // 대회 알림
                0 -> NotificationHistory(notifications = notifications.filter { it.type == NotificationType.CONTEST })

                // 활동 알림 + 소명 알림
                1 -> NotificationHistory(notifications = notifications.filter { it.type == NotificationType.ACTION || it.type == NotificationType.VINDICATION })

                // 공지 알림
                2 -> NotificationHistory(notifications = notifications.filter { it.type == NotificationType.ANNOUNCEMENT })
            }
        }
    }
}

@DevicePreviews
@Composable
private fun NotificationBodyPreview() {
    NotificationBody(notifications = mockNotifications)
}
