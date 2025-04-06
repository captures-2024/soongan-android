package com.captures2024.soongan.feature.profile.ui.notification

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.core.model.mock.mockNotificationsCountTable
import com.captures2024.soongan.core.model.mock.mockNotificationsTable
import com.captures2024.soongan.core.model.utils.NotificationType
import com.captures2024.soongan.core.model.utils.NotificationsCountTable
import com.captures2024.soongan.core.model.utils.NotificationsTable
import com.captures2024.soongan.feature.profile.R
import com.captures2024.soongan.feature.profile.ui.component.CustomTabRow
import com.captures2024.soongan.feature.profile.utils.nonScaleAnnotatedTitle

@Composable
internal fun NotificationTab(
    notificationsTable: NotificationsTable,
    notificationsCountTable: NotificationsCountTable,
    modifier: Modifier = Modifier,
    onClickNotification : (NotificationType, Int) -> Unit = { _, _ -> },
    onDeleteNotification : (NotificationType, Int) -> Unit = { _, _ -> },
) {
    val notificationTypes = NotificationType.entries.toList()

    val tabs = notificationTypes.map { type ->
        nonScaleAnnotatedTitle(
            title = stringResource(
                when (type) {
                    NotificationType.CONTEST -> R.string.contest_notification_title
                    NotificationType.ACTIVITY -> R.string.user_action_notification_title
                    NotificationType.NOTICE -> R.string.announce_notification_title
                }
            ),
            count = notificationsCountTable[type] ?: 0
        )
    }

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
            val type = notificationTypes[page]

            NotificationBody(
                notifications = notificationsTable[type] ?: emptyMap(),
                onClickNotification = { onClickNotification(type, it) },
                onDeleteNotification = { onDeleteNotification(type, it) },
            )
        }
    }
}

@DevicePreviews
@Composable
private fun NotificationBodyPreview() {
    NotificationTab(
        notificationsTable = mockNotificationsTable,
        notificationsCountTable = mockNotificationsCountTable
    )
}
