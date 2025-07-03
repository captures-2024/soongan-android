package com.captures2024.soongan.domain.usecase.notification.impl

import com.captures2024.soongan.core.model.dto.NotificationSettingDto
import com.captures2024.soongan.domain.repository.notification.NotificationRepository
import com.captures2024.soongan.domain.usecase.notification.PatchNotificationSettingsUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class PatchNotificationSettingsUseCaseImpl
@Inject
constructor(
    private val notificationRepository: NotificationRepository,
) : PatchNotificationSettingsUseCase {

    override suspend operator fun invoke(settings: NotificationSettingDto): Result<NotificationSettingDto> = runSuspendCatching{
        return@runSuspendCatching notificationRepository.patchNotificationSettings(
            settings = settings,
        )
    }
}
