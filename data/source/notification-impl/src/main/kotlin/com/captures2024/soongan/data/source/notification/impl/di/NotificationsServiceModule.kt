package com.captures2024.soongan.data.source.notification.impl.di

import com.captures2024.soongan.data.source.notification.impl.service.NotificationsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NotificationsServiceModule {

    @Singleton
    @Provides
    fun provideNotificationsService(retrofit: Retrofit): NotificationsService = retrofit.create(NotificationsService::class.java)
}
