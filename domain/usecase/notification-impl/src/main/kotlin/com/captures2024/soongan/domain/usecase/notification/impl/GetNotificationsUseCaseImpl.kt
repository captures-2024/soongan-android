package com.captures2024.soongan.domain.usecase.notification.impl

import com.captures2024.soongan.core.model.dto.NotificationsInfoDto
import com.captures2024.soongan.core.model.utils.NotificationType
import com.captures2024.soongan.data.repository.notification.NotificationRepository
import com.captures2024.soongan.domain.usecase.notification.GetNotificationsUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class GetNotificationsUseCaseImpl
@Inject
constructor(
    private val repository: NotificationRepository,
) : GetNotificationsUseCase {

    override suspend fun invoke(type: NotificationType): Result<NotificationsInfoDto> = runSuspendCatching {
        val notificationsDto = repository.getNotifications(type = type)

        return@runSuspendCatching notificationsDto.copy(
            notifications = notificationsDto.notifications.sorted(),
        )
    }
}
