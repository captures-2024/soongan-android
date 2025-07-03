package com.captures2024.soongan.domain.usecase.notification

import com.captures2024.soongan.core.model.dto.NotificationSettingDto

interface PatchNotificationSettingsUseCase {

    suspend operator fun invoke(settings: NotificationSettingDto): Result<NotificationSettingDto>
}
