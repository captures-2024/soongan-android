package com.captures2024.soongan.domain.usecase.notification.impl

import com.captures2024.soongan.data.repository.notification.NotificationRepository
import com.captures2024.soongan.domain.usecase.notification.DeleteNotificationUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class DeleteNotificationUseCaseImpl
@Inject
constructor(
    private val repository: NotificationRepository,
) : DeleteNotificationUseCase {

    override suspend fun invoke(notificationId: Long): Result<Boolean> = runSuspendCatching {
        return@runSuspendCatching repository.deleteNotification(notificationId = notificationId)
    }
}
