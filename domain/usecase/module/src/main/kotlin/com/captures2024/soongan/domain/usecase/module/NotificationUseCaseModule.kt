package com.captures2024.soongan.domain.usecase.module

import com.captures2024.soongan.domain.usecase.notification.DeleteNotificationUseCase
import com.captures2024.soongan.domain.usecase.notification.EmitNotificationUseCase
import com.captures2024.soongan.domain.usecase.notification.GetNotificationsCountUseCase
import com.captures2024.soongan.domain.usecase.notification.GetNotificationsUseCase
import com.captures2024.soongan.domain.usecase.notification.PostNotificationReadUseCase
import com.captures2024.soongan.domain.usecase.notification.impl.DeleteNotificationUseCaseImpl
import com.captures2024.soongan.domain.usecase.notification.impl.EmitNotificationUseCaseImpl
import com.captures2024.soongan.domain.usecase.notification.impl.GetNotificationsCountUseCaseImpl
import com.captures2024.soongan.domain.usecase.notification.impl.GetNotificationsUseCaseImpl
import com.captures2024.soongan.domain.usecase.notification.impl.PostNotificationReadUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class NotificationUseCaseModule {

    @Binds
    abstract fun bindDeleteNotificationUseCase(deleteNotificationUseCaseImpl: DeleteNotificationUseCaseImpl): DeleteNotificationUseCase

    @Binds
    abstract fun bindEmitNotificationUseCase(emitNotificationUseCaseImpl: EmitNotificationUseCaseImpl): EmitNotificationUseCase

    @Binds
    abstract fun bindGetNotificationsCountUseCase(getNotificationsCountUseCaseImpl: GetNotificationsCountUseCaseImpl): GetNotificationsCountUseCase

    @Binds
    abstract fun bindGetNotificationsUseCase(getNotificationsUseCaseImpl: GetNotificationsUseCaseImpl): GetNotificationsUseCase

    @Binds
    abstract fun bindPostNotificationReadUseCase(postNotificationReadUseCaseImpl: PostNotificationReadUseCaseImpl): PostNotificationReadUseCase
}
