package com.captures2024.soongan.core.data.source.notification.local

import com.captures2024.soongan.core.model.dto.NotificationDto
import kotlinx.coroutines.flow.Flow

interface NotificationLocalDataSource {

    val notificationToken: Flow<Map<String, String?>>

    val notificationEvent: Flow<NotificationDto?>

    fun emitNotification(payload: Map<String, Any?>)

    suspend fun parseNotification(payload: Map<String, Any?>)
}
