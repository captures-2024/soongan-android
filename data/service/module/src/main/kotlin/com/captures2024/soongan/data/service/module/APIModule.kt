package com.captures2024.soongan.data.service.module

import com.captures2024.soongan.data.service.api.AuthAPI
import com.captures2024.soongan.data.service.api.FcmAPI
import com.captures2024.soongan.data.service.api.HomeAPI
import com.captures2024.soongan.data.service.api.MembersAPI
import com.captures2024.soongan.data.service.api.NotificationsAPI
import com.captures2024.soongan.data.service.api.PostLikeAPI
import com.captures2024.soongan.data.service.api.ReportAPI
import com.captures2024.soongan.data.service.api.WeeklyContestAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object APIModule {

    @Singleton
    @Provides
    fun provideAuthAPI(retrofit: Retrofit): AuthAPI = retrofit.create(AuthAPI::class.java)

    @Singleton
    @Provides
    fun providePostLikeAPI(retrofit: Retrofit): PostLikeAPI = retrofit.create(PostLikeAPI::class.java)

    @Singleton
    @Provides
    fun provideWeeklyContestAPI(retrofit: Retrofit): WeeklyContestAPI = retrofit.create(WeeklyContestAPI::class.java)

    @Singleton
    @Provides
    fun provideFcmAPI(retrofit: Retrofit): FcmAPI = retrofit.create(FcmAPI::class.java)

    @Singleton
    @Provides
    fun provideHomeAPI(retrofit: Retrofit): HomeAPI = retrofit.create(HomeAPI::class.java)

    @Singleton
    @Provides
    fun provideMembersAPI(retrofit: Retrofit): MembersAPI = retrofit.create(MembersAPI::class.java)

    @Singleton
    @Provides
    fun provideNotificationsAPI(retrofit: Retrofit): NotificationsAPI = retrofit.create(NotificationsAPI::class.java)

    @Singleton
    @Provides
    fun provideReportAPI(retrofit: Retrofit): ReportAPI = retrofit.create(ReportAPI::class.java)
}
