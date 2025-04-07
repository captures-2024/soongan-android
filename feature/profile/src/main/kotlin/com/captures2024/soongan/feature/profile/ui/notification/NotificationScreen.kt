package com.captures2024.soongan.feature.profile.ui.notification

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.core.viewmodel.profile.NotificationViewModel
import com.captures2024.soongan.feature.profile.ui.component.CustomTopBar

@Composable
internal fun NotificationScreen(
    uiState: NotificationViewModel.State,
    intent: (NotificationViewModel.Intent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CustomTopBar(
                text = "알림",
                onBackPressed = { intent(NotificationViewModel.Intent.OnBackPressed) },
            )
        },
        containerColor = SGColor.white,
    ) { paddingValues ->
        NotificationTab(
            modifier = modifier.padding(paddingValues),
            notificationsTable = uiState.notifications,
            notificationsCountTable = uiState.notificationsCount,
            onClickNotification = { type, id ->
                intent(NotificationViewModel.Intent.OnClickNotification(type, id))
            },
            onDeleteNotification = { type, id ->
                intent(NotificationViewModel.Intent.OnDeleteNotification(type, id))
            },
        )
    }
}

@DevicePreviews
@Composable
private fun NotificationScreenPreview() {
    NotificationScreen(uiState = NotificationViewModel.State(), intent = {})
}
