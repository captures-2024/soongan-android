package com.captures2024.soongan.feature.profile.ui.notification

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.model.UserNotification
import com.captures2024.soongan.core.model.mock.mockNotifications

@Composable
internal fun NotificationScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {},
    allNotifications: List<UserNotification> = mockNotifications,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            NotificationTopBar(onBackPressed = onBackPressed)
        },
        containerColor = Color.White
    ) { paddingValues ->
        NotificationBody(
            modifier = modifier.padding(paddingValues),
            notifications = allNotifications
        )
    }
}

@DevicePreviews
@Composable
private fun NotificationScreenPreview() {
    NotificationScreen(allNotifications = mockNotifications)
}