package com.captures2024.soongan.data.source.notification.impl.mapper

import com.captures2024.soongan.core.model.dto.NotificationSettingDto
import com.captures2024.soongan.core.model.network.request.notifications.PatchNotificationSettingsRequest

internal fun PatchNotificationSettingsRequest.toNotificationSettingDto(): NotificationSettingDto = NotificationSettingDto(
    contestPush = this.contestPush,
    activityPush = this.activityPush,
    noticePush = this.noticePush,
)

internal fun NotificationSettingDto.toPatchNotificationSettingsRequest(): PatchNotificationSettingsRequest = PatchNotificationSettingsRequest(
    contestPush = this.contestPush,
    activityPush = this.activityPush,
    noticePush = this.noticePush,
)
