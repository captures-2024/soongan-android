package com.captures2024.soongan.domain.usecase.notification.impl

import com.captures2024.soongan.core.model.dto.NotificationSettingDto
import com.captures2024.soongan.domain.repository.notification.NotificationRepository
import com.captures2024.soongan.domain.usecase.notification.GetNotificationSettingsUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class GetNotificationSettingsUseCaseImpl
@Inject
constructor(
    private val repository: NotificationRepository,
) : GetNotificationSettingsUseCase {

    override suspend operator fun invoke(): Result<NotificationSettingDto> = runSuspendCatching {
        val notificationSettings = repository.getNotificationSettings()

        return@runSuspendCatching notificationSettings
    }
}