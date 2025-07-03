package com.captures2024.soongan.data.source.notification.impl.mapper

import com.captures2024.soongan.core.model.dto.NotificationSettingDto
import com.captures2024.soongan.core.model.network.response.notifications.GetNotificationSettingsResponse

internal fun GetNotificationSettingsResponse.toNotificationSettingDto(): NotificationSettingDto = NotificationSettingDto(
    contestPush = this.contestPush,
    activityPush = this.activityPush,
    noticePush = this.noticePush,
)

internal fun NotificationSettingDto.toGetNotificationSettingsResponse(): GetNotificationSettingsResponse = GetNotificationSettingsResponse(
    contestPush = this.contestPush,
    activityPush = this.activityPush,
    noticePush = this.noticePush,
)
