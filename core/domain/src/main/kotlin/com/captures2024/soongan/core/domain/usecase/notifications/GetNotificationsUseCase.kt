package com.captures2024.soongan.core.domain.usecase.notifications

import com.captures2024.soongan.core.data.repository.NotificationsRepository
import com.captures2024.soongan.core.domain.runSuspendCatching
import com.captures2024.soongan.core.model.dto.NotificationsDto
import com.captures2024.soongan.core.model.utils.NotificationType
import javax.inject.Inject

class GetNotificationsUseCase
@Inject
constructor(
    private val repository: NotificationsRepository,
) {

    suspend operator fun invoke(type: NotificationType): Result<NotificationsDto> = runSuspendCatching {
        val notificationsDto = repository.getNotifications(type = type)

        return@runSuspendCatching notificationsDto
    }
}
