package com.captures2024.soongan.presentation.feature.main.profile.component.notification

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.core.model.mock.mockNotificationsCountTable
import com.captures2024.soongan.core.model.mock.mockNotificationsTable
import com.captures2024.soongan.core.model.utils.NotificationType
import com.captures2024.soongan.presentation.viewmodel.main.profile.ProfileNotificationViewModel

@Composable
internal fun NotificationBodyComponent(
    state: ProfileNotificationViewModel.State,
    modifier: Modifier = Modifier,
    onClickCategory: (NotificationType) -> Unit,
    onClickNotification: (Int) -> Unit,
    onDeleteNotification: (Int) -> Unit,
) {
    val pagerState = rememberPagerState(
        pageCount = { state.notificationCategories.size },
        initialPageOffsetFraction = 0f,
        initialPage = 0,
    )

    Column(modifier = modifier) {
        NotificationTapRowComponent(
            tabs = state.notificationCategories,
            selectedCategory = state.selectedNotificationCategory,
            notificationsCountTable = state.notificationsCount,
            pagerState = pagerState,
            onClickCategory = onClickCategory,
        )

        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false,
        ) { page ->
            NotificationPageComponent(
                notifications = state.notifications[state.selectedNotificationCategory] ?: emptyMap(),
                onClickNotification = onClickNotification,
                onDeleteNotification = onDeleteNotification,
            )
        }
    }
}

@DevicePreviews
@Composable
private fun PreviewNotificationBodyComponent() {
    SGTheme {
        NotificationBodyComponent(
            state = ProfileNotificationViewModel.State(
                notificationCategories = NotificationType.entries,
                selectedNotificationCategory = NotificationType.CONTEST,
                notifications = mockNotificationsTable,
                notificationsCount = mockNotificationsCountTable,
            ),
            onClickCategory = {},
            onClickNotification = {},
            onDeleteNotification = {},
        )
    }
}
