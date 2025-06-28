package com.captures2024.soongan.data.source.notification.impl.remote

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.model.dto.NotificationsCountInfoDto
import com.captures2024.soongan.core.model.dto.NotificationsInfoDto
import com.captures2024.soongan.core.model.utils.NotificationType
import com.captures2024.soongan.data.source.notification.impl.mapper.toNotificationsCountDto
import com.captures2024.soongan.data.source.notification.impl.mapper.toNotificationsDto
import com.captures2024.soongan.data.source.notification.remote.NotificationsRemoteDataSource
import com.captures2024.soongan.data.service.api.NotificationsService
import com.captures2024.soongan.data.service.api.utils.safeAPICall
import javax.inject.Inject

class NotificationsRemoteDataSourceImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val service: NotificationsService,
) : NotificationsRemoteDataSource {

    init {
        analyticsHelper.d { "NotificationsDataSource::init" }
    }

    override suspend fun getNotifications(type: NotificationType): NotificationsInfoDto? {
        analyticsHelper.d { "getNotifications - type: $type" }

        val response = safeAPICall { service.getNotifications(type = type.name) }

        val responseHeader = response.headers

        analyticsHelper.d { "getNotifications - responseHeader: $responseHeader" }

        val responseBody = response.body

        analyticsHelper.d { "getNotifications - responseBody: $responseBody" }

        return responseBody?.responseData?.toNotificationsDto()
    }

    override suspend fun getNotificationsCount(): NotificationsCountInfoDto? {
        analyticsHelper.d { "getNotificationsCount - entry" }

        val response = safeAPICall { service.getNotificationsCount() }

        val responseHeader = response.headers

        analyticsHelper.d { "getNotificationsCount - responseHeader: $responseHeader" }

        val responseBody = response.body

        analyticsHelper.d { "getNotificationsCount - responseBody: $responseBody" }

        return responseBody?.responseData?.toNotificationsCountDto()
    }

    override suspend fun postNotificationRead(notificationId: Long): Boolean {
        analyticsHelper.d { "postNotificationRead - notificationId: $notificationId" }

        val response = safeAPICall { service.postNotificationRead(notificationId = notificationId) }

        val responseHeader = response.headers

        analyticsHelper.d { "postNotificationRead - responseHeader: $responseHeader" }

        val responseBody = response.body

        analyticsHelper.d { "postNotificationRead - responseBody: $responseBody" }

        return when (responseBody) {
            null -> false
            else -> true
        }
    }

    override suspend fun deleteNotification(notificationId: Long): Boolean {
        analyticsHelper.d { "deleteNotification - notificationId: $notificationId" }

        val response = safeAPICall { service.deleteNotification(notificationId = notificationId) }

        val responseHeader = response.headers

        analyticsHelper.d { "deleteNotification - responseHeader: $responseHeader" }

        val responseBody = response.body

        analyticsHelper.d { "deleteNotification - responseBody: $responseBody" }

        return when (responseBody) {
            null -> false
            else -> true
        }
    }
}
