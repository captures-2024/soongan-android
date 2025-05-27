package com.captures2024.soongan.presentation.feature.main.profile.component.notification

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.captures2024.soongan.core.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.core.model.utils.NotificationType
import com.captures2024.soongan.presentation.viewmodel.main.profile.ProfileNotificationViewModel

@Composable
internal fun NotificationScreen(
    state: ProfileNotificationViewModel.State,
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
    onClickCategory: (NotificationType) -> Unit,
    onClickNotification: (Int) -> Unit,
    onDeleteNotification: (Int) -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = @Composable {
            NotificationTopBarComponent(
                onClickBack = onClickBack,
            )
        },
    ) { paddingValues ->
        NotificationBodyComponent(
            state = state,
            modifier = Modifier.padding(paddingValues),
            onClickCategory = onClickCategory,
            onClickNotification = onClickNotification,
            onDeleteNotification = onDeleteNotification,
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewNotificationScreen() {
    SGTheme {
        NotificationScreen(
            state = ProfileNotificationViewModel.State(
                notificationCategories = NotificationType.entries,
                selectedNotificationCategory = NotificationType.CONTEST,
                notifications = emptyMap(),
                notificationsCount = emptyMap(),
            ),
            onClickBack = {},
            onClickCategory = {},
            onClickNotification = {},
            onDeleteNotification = {},
        )
    }
}
