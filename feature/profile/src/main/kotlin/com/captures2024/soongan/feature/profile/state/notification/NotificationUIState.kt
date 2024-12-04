package com.captures2024.soongan.feature.profile.state.notification

import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.UIState
import java.time.LocalDateTime

internal data class NotificationUIState(
    val isLoading: Boolean = false,
    val notification: List<MockNotification> = emptyList(),
): UIState {

    override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
        LogElementArgument("isLoading", isLoading.toString()),
        LogElementArgument("notification", notification.toString()),
    )
}

internal data class MockNotification(
    val type: String = "",
    val title: String = "",
    val content: String = "",
    val createdAt: LocalDateTime = LocalDateTime.now(),
)