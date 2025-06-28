package com.captures2024.soongan.data.repository.module

import com.captures2024.soongan.data.repository.notification.impl.NotificationRepositoryImpl
import com.captures2024.soongan.domain.repository.notification.NotificationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NotificationRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindNotificationRepository(notificationRepositoryImpl: NotificationRepositoryImpl): NotificationRepository
}