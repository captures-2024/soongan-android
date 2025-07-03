package com.captures2024.soongan.data.source.notification.impl.remote

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.model.dto.NotificationSettingDto
import com.captures2024.soongan.core.model.dto.NotificationsCountInfoDto
import com.captures2024.soongan.core.model.dto.NotificationsInfoDto
import com.captures2024.soongan.core.model.utils.NotificationType
import com.captures2024.soongan.data.service.api.NotificationsAPI
import com.captures2024.soongan.data.service.api.utils.safeAPICall
import com.captures2024.soongan.data.source.notification.impl.mapper.toNotificationSettingDto
import com.captures2024.soongan.data.source.notification.impl.mapper.toNotificationsCountDto
import com.captures2024.soongan.data.source.notification.impl.mapper.toNotificationsDto
import com.captures2024.soongan.data.source.notification.impl.mapper.toPatchNotificationSettingsRequest
import com.captures2024.soongan.data.source.notification.remote.NotificationsRemoteDataSource
import javax.inject.Inject

class NotificationsRemoteDataSourceImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val notificationsAPI: NotificationsAPI,
) : NotificationsRemoteDataSource {

    init {
        analyticsHelper.d { "NotificationsDataSource::init" }
    }

    override suspend fun getNotifications(type: NotificationType): NotificationsInfoDto? {
        analyticsHelper.d { "getNotifications - type: $type" }

        val response = safeAPICall { notificationsAPI.getNotifications(type = type.name) }

        val responseHeader = response.headers

        analyticsHelper.d { "getNotifications - responseHeader: $responseHeader" }

        val responseBody = response.body

        analyticsHelper.d { "getNotifications - responseBody: $responseBody" }

        return responseBody?.responseData?.toNotificationsDto()
    }

    override suspend fun getNotificationsCount(): NotificationsCountInfoDto? {
        analyticsHelper.d { "getNotificationsCount - entry" }

        val response = safeAPICall { notificationsAPI.getNotificationsCount() }

        val responseHeader = response.headers

        analyticsHelper.d { "getNotificationsCount - responseHeader: $responseHeader" }

        val responseBody = response.body

        analyticsHelper.d { "getNotificationsCount - responseBody: $responseBody" }

        return responseBody?.responseData?.toNotificationsCountDto()
    }

    override suspend fun postNotificationRead(notificationId: Long): Boolean {
        analyticsHelper.d { "postNotificationRead - notificationId: $notificationId" }

        val response = safeAPICall { notificationsAPI.postNotificationRead(notificationId = notificationId) }

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

        val response = safeAPICall { notificationsAPI.deleteNotification(notificationId = notificationId) }

        val responseHeader = response.headers

        analyticsHelper.d { "deleteNotification - responseHeader: $responseHeader" }

        val responseBody = response.body

        analyticsHelper.d { "deleteNotification - responseBody: $responseBody" }

        return when (responseBody) {
            null -> false
            else -> true
        }
    }

    override suspend fun getNotificationSettings(): NotificationSettingDto? {
        analyticsHelper.d { "getNotificationSettings" }

        val response = safeAPICall { notificationsAPI.getNotificationSettings() }

        val responseHeader = response.headers

        analyticsHelper.d { "getNotificationSettings - responseHeader: $responseHeader" }

        val responseBody = response.body

        analyticsHelper.d { "getNotificationSettings - responseBody: $responseBody" }

        return responseBody?.responseData?.toNotificationSettingDto()
    }

    override suspend fun patchNotificationSettings(settings: NotificationSettingDto): NotificationSettingDto? {
        analyticsHelper.d { "patchNotificationSettings - settings: $settings" }

        val response = safeAPICall {
            notificationsAPI.patchNotificationSettings(
                request = settings.toPatchNotificationSettingsRequest(),
            )
        }

        val responseHeader = response.headers

        analyticsHelper.d { "patchNotificationSettings - responseHeader: $responseHeader" }

        val responseBody = response.body

        analyticsHelper.d { "patchNotificationSettings - responseBody: $responseBody" }

        return responseBody?.responseData?.toNotificationSettingDto()
    }
}
