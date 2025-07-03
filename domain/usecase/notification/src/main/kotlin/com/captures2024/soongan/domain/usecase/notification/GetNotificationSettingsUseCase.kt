package com.captures2024.soongan.domain.usecase.notification

import com.captures2024.soongan.core.model.dto.NotificationSettingDto

interface GetNotificationSettingsUseCase {

    suspend operator fun invoke(): Result<NotificationSettingDto>
}
