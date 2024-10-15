package com.captures2024.soongan.core.data.di

import com.captures2024.soongan.core.data.service.FcmService
import com.captures2024.soongan.core.data.service.MembersService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Singleton
    @Provides
    internal fun provideMembersService(retrofit: Retrofit): MembersService = retrofit.create(MembersService::class.java)

    @Singleton
    @Provides
    internal fun provideFcmService(retrofit: Retrofit): FcmService = retrofit.create(FcmService::class.java)
}
