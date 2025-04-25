package com.captures2024.soongan.core.data.repository.impl

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.data.repository.NotificationsRepository
import com.captures2024.soongan.core.data.source.notification.local.NotificationLocalDataSource
import com.captures2024.soongan.core.data.source.notification.remote.NotificationsRemoteDataSource
import com.captures2024.soongan.core.model.dto.NotificationDto
import com.captures2024.soongan.core.model.dto.NotificationsCountInfoDto
import com.captures2024.soongan.core.model.dto.NotificationsInfoDto
import com.captures2024.soongan.core.model.utils.NotificationType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotificationsRepositoryImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val notificationLocalDataSource: NotificationLocalDataSource,
    private val notificationsRemoteDataSource: NotificationsRemoteDataSource,
) : NotificationsRepository {
    private val scope by lazy { CoroutineScope(Dispatchers.Default + SupervisorJob()) }

    override val notificationEvent: Flow<NotificationDto?>
        get() = notificationLocalDataSource.notificationEvent

    init {
        analyticsHelper.d { "NotificationsRepository:init" }

        scope.launch {
            notificationLocalDataSource.notificationToken.collect {
                notificationLocalDataSource.parseNotification(it)
            }
        }
    }

    override fun emitNotification(payload: Map<String, Any?>) {
        notificationLocalDataSource.emitNotification(payload)
    }

    override suspend fun getNotifications(type: NotificationType): NotificationsInfoDto {
        val notifications = notificationsRemoteDataSource.getNotifications(type = type)

        return notifications ?: throw NullPointerException("notificationsDto is null")
    }

    override suspend fun getNotificationsCount(): NotificationsCountInfoDto {
        val notificationsCount = notificationsRemoteDataSource.getNotificationsCount()

        return notificationsCount ?: throw NullPointerException("notificationsCountDto is null")
    }

    override suspend fun postNotificationRead(notificationId: Long): Boolean {
        analyticsHelper.d { "postNotificationRead - notificationId: $notificationId" }
        return notificationsRemoteDataSource.postNotificationRead(notificationId = notificationId)
    }

    override suspend fun deleteNotification(notificationId: Long): Boolean {
        analyticsHelper.d { "deleteNotification - notificationId: $notificationId" }
        return notificationsRemoteDataSource.deleteNotification(notificationId = notificationId)
    }
}
