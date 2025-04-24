package com.captures2024.soongan.core.data.di

import com.captures2024.soongan.core.data.source.auth.remote.AuthRemoteDataSource
import com.captures2024.soongan.core.data.source.fcm.remote.FcmRemoteDataSource
import com.captures2024.soongan.core.data.source.home.remote.HomeRemoteDataSource
import com.captures2024.soongan.core.data.source.members.remote.MembersRemoteDataSource
import com.captures2024.soongan.core.data.source.notification.remote.NotificationsRemoteDataSource
import com.captures2024.soongan.core.data.source.post_like.remote.PostLikeRemoteDataSource
import com.captures2024.soongan.core.data.source.report.remote.ReportRemoteDataSource
import com.captures2024.soongan.core.data.source.weekly_contest.remote.WeeklyContestRemoteDataSource
import com.captures2024.soongan.core.data.source.auth.remote.AuthRemoteDataSourceImpl
import com.captures2024.soongan.core.data.source.fcm.remote.FcmRemoteDataSourceImpl
import com.captures2024.soongan.core.data.source.home.remote.HomeRemoteDataSourceImpl
import com.captures2024.soongan.core.data.source.members.remote.MembersRemoteDataSourceImpl
import com.captures2024.soongan.core.data.source.notification.remote.NotificationsRemoteDataSourceImpl
import com.captures2024.soongan.core.data.source.post_like.remote.PostLikeRemoteDataSourceImpl
import com.captures2024.soongan.core.data.source.report.remote.ReportRemoteDataSourceImpl
import com.captures2024.soongan.core.data.source.weekly_contest.remote.WeeklyContestRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindMembersRemoteDataSource(membersRemoteDataSourceImpl: MembersRemoteDataSourceImpl): MembersRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindFcmRemoteDataSource(fcmRemoteDataSourceImpl: FcmRemoteDataSourceImpl): FcmRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindAuthRemoteDataSource(authRemoteDataSourceImpl: AuthRemoteDataSourceImpl): AuthRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindHomeRemoteDataSource(homeRemoteDataSourceImpl: HomeRemoteDataSourceImpl): HomeRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindWeeklyContestRemoteDataSource(weeklyContestRemoteDataSourceImpl: WeeklyContestRemoteDataSourceImpl): WeeklyContestRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindReportRemoteDataSource(reportRemoteDataSourceImpl: ReportRemoteDataSourceImpl): ReportRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindPostLikeRemoteDataSource(postLikeRemoteDataSourceImpl: PostLikeRemoteDataSourceImpl): PostLikeRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindNotificationsRemoteDataSource(notificationsRemoteDataSourceImpl: NotificationsRemoteDataSourceImpl): NotificationsRemoteDataSource
}
