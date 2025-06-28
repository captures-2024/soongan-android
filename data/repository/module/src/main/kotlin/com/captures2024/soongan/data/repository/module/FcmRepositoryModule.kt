package com.captures2024.soongan.data.repository.module

import com.captures2024.soongan.data.repository.fcm.impl.FcmRepositoryImpl
import com.captures2024.soongan.domain.repository.fcm.FcmRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class FcmRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindFcmRepository(fcmRepositoryImpl: FcmRepositoryImpl): FcmRepository
}