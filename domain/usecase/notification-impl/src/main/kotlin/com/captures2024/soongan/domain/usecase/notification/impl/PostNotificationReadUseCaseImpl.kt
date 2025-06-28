package com.captures2024.soongan.domain.usecase.notification.impl

import com.captures2024.soongan.domain.repository.notification.NotificationRepository
import com.captures2024.soongan.domain.usecase.notification.PostNotificationReadUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class PostNotificationReadUseCaseImpl
@Inject
constructor(
    private val repository: NotificationRepository,
) : PostNotificationReadUseCase {

    override suspend fun invoke(notificationId: Long): Result<Boolean> = runSuspendCatching {
        return@runSuspendCatching repository.postNotificationRead(notificationId = notificationId)
    }
}
