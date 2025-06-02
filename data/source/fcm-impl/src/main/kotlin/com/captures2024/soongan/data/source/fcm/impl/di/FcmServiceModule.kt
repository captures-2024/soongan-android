package com.captures2024.soongan.data.source.fcm.impl.di

import com.captures2024.soongan.data.source.fcm.impl.service.FcmService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object FcmServiceModule {

    @Singleton
    @Provides
    fun provideFcmService(retrofit: Retrofit): FcmService = retrofit.create(FcmService::class.java)
}
