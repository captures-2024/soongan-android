package com.captures2024.soongan.domain.usecase.notification.impl

import com.captures2024.soongan.core.model.dto.NotificationsCountInfoDto
import com.captures2024.soongan.domain.repository.notification.NotificationRepository
import com.captures2024.soongan.domain.usecase.notification.GetUnreadNotificationsCountUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class GetUnreadNotificationsCountUseCaseImpl
@Inject
constructor(
    private val repository: NotificationRepository,
) : GetUnreadNotificationsCountUseCase {

    override suspend fun invoke(): Result<NotificationsCountInfoDto> = runSuspendCatching {
        val notificationsCountDto = repository.getUnreadNotificationsCount()

        return@runSuspendCatching notificationsCountDto
    }
}
