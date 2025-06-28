package com.captures2024.soongan.data.service.module

import com.captures2024.soongan.data.service.api.PostLikeService
import com.captures2024.soongan.data.service.api.WeeklyContestService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ContestServiceModule {

    @Singleton
    @Provides
    fun providePostLikeService(retrofit: Retrofit): PostLikeService = retrofit.create(PostLikeService::class.java)

    @Singleton
    @Provides
    fun provideWeeklyContestService(retrofit: Retrofit): WeeklyContestService = retrofit.create(WeeklyContestService::class.java)
}