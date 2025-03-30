package com.captures2024.soongan.core.domain.usecase.notifications

import com.captures2024.soongan.core.data.repository.NotificationsRepository
import com.captures2024.soongan.core.domain.runSuspendCatching
import com.captures2024.soongan.core.model.dto.NotificationsCountDto
import javax.inject.Inject

class GetNotificationsCountUseCase
@Inject
constructor(
    private val repository: NotificationsRepository,
) {

    suspend operator fun invoke(): Result<NotificationsCountDto> = runSuspendCatching {
        val notificationsCountDto = repository.getNotificationsCount()

        return@runSuspendCatching notificationsCountDto
    }
}
