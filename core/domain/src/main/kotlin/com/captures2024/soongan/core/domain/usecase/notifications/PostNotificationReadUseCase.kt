package com.captures2024.soongan.core.domain.usecase.notifications

import com.captures2024.soongan.core.data.repository.NotificationsRepository
import com.captures2024.soongan.core.domain.runSuspendCatching
import javax.inject.Inject

class PostNotificationReadUseCase
@Inject
constructor(
    private val repository: NotificationsRepository,
) {

    suspend operator fun invoke(notificationId: Long): Result<Boolean> = runSuspendCatching {
        return@runSuspendCatching repository.postNotificationRead(notificationId = notificationId)
    }
}
