package com.captures2024.soongan.feature.profile.state.notification

import java.time.LocalDateTime

internal data class NotificationUiState(
    val isLoading: Boolean = false,
    val notification: List<MockNotification> = emptyList(),
)

internal data class MockNotification(
    val type: String = "",
    val title: String = "",
    val content: String = "",
    val createdAt: LocalDateTime = LocalDateTime.now(),
)