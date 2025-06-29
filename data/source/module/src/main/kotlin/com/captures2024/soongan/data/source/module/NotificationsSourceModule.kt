package com.captures2024.soongan.data.source.module

import com.captures2024.soongan.data.source.notification.impl.local.NotificationLocalDataSourceImpl
import com.captures2024.soongan.data.source.notification.impl.remote.NotificationsRemoteDataSourceImpl
import com.captures2024.soongan.data.source.notification.local.NotificationLocalDataSource
import com.captures2024.soongan.data.source.notification.remote.NotificationsRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NotificationsSourceModule {

    @Binds
    @Singleton
    abstract fun bindNotificationLocalDataSource(notificationLocalDataSourceImpl: NotificationLocalDataSourceImpl): NotificationLocalDataSource

    @Binds
    @Singleton
    abstract fun bindNotificationsRemoteDataSource(notificationsRemoteDataSourceImpl: NotificationsRemoteDataSourceImpl): NotificationsRemoteDataSource
}
