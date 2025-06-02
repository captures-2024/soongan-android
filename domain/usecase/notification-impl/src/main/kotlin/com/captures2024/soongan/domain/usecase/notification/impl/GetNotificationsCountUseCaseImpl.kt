package com.captures2024.soongan.domain.usecase.notification.impl

import com.captures2024.soongan.core.model.dto.NotificationsCountInfoDto
import com.captures2024.soongan.data.repository.notification.NotificationRepository
import com.captures2024.soongan.domain.usecase.notification.GetNotificationsCountUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class GetNotificationsCountUseCaseImpl
@Inject
constructor(
    private val repository: NotificationRepository,
) : GetNotificationsCountUseCase {

    override suspend fun invoke(): Result<NotificationsCountInfoDto> = runSuspendCatching {
        val notificationsCountDto = repository.getNotificationsCount()

        return@runSuspendCatching notificationsCountDto
    }
}
