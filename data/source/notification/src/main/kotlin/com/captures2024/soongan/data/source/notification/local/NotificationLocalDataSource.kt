package com.captures2024.soongan.data.source.notification.local

import com.captures2024.soongan.core.model.dto.NotificationDto
import com.captures2024.soongan.core.model.dto.fcm.CloudMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface NotificationLocalDataSource {

    val notificationToken: Flow<Map<String, String?>>

    val notificationEvent: Flow<NotificationDto?>

    val cloudMessageEvent: Flow<CloudMessage?>

    val isNotReadNotificationCache: StateFlow<Boolean>

    fun emitNotification(payload: Map<String, Any?>)

    suspend fun parseNotification(payload: Map<String, Any?>)

    fun postIsNotReadNotificationCache(value: Boolean)
}
