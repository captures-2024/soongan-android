package com.captures2024.soongan.core.data.repository.impl

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.data.source.notification.remote.NotificationsRemoteDataSourceImpl
import com.captures2024.soongan.core.data.repository.NotificationsRepository
import com.captures2024.soongan.core.model.dto.NotificationsCountInfoDto
import com.captures2024.soongan.core.model.dto.NotificationsInfoDto
import com.captures2024.soongan.core.model.utils.NotificationType
import javax.inject.Inject

class NotificationsRepositoryImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val dataSourceImpl: NotificationsRemoteDataSourceImpl,
) : NotificationsRepository {

    init {
        analyticsHelper.d { "NotificationsRepository:init" }
    }

    override suspend fun getNotifications(type: NotificationType): NotificationsInfoDto {
        val notifications = dataSourceImpl.getNotifications(type = type)

        return notifications ?: throw NullPointerException("notificationsDto is null")
    }

    override suspend fun getNotificationsCount(): NotificationsCountInfoDto {
        val notificationsCount = dataSourceImpl.getNotificationsCount()

        return notificationsCount ?: throw NullPointerException("notificationsCountDto is null")
    }

    override suspend fun postNotificationRead(notificationId: Long): Boolean {
        analyticsHelper.d { "postNotificationRead - notificationId: $notificationId" }
        return dataSourceImpl.postNotificationRead(notificationId = notificationId)
    }

    override suspend fun deleteNotification(notificationId: Long): Boolean {
        analyticsHelper.d { "deleteNotification - notificationId: $notificationId" }
        return dataSourceImpl.deleteNotification(notificationId = notificationId)
    }
}
