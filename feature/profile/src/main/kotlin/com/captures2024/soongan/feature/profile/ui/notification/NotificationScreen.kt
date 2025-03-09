package com.captures2024.soongan.feature.profile.ui.notification

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.core.model.UserNotification
import com.captures2024.soongan.core.model.mock.mockNotifications
import com.captures2024.soongan.feature.profile.ui.component.CustomTopBar

@Composable
internal fun NotificationScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {},
    allNotifications: List<UserNotification> = mockNotifications,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CustomTopBar(text = "알림", onBackPressed = onBackPressed)
        },
        containerColor = SGColor.white,
    ) { paddingValues ->
        NotificationBody(
            modifier = modifier.padding(paddingValues),
            notifications = allNotifications,
        )
    }
}

@DevicePreviews
@Composable
private fun NotificationScreenPreview() {
    NotificationScreen(allNotifications = mockNotifications)
}
