package com.captures2024.soongan.core.data.di

import com.captures2024.soongan.core.data.remote.AuthDataSource
import com.captures2024.soongan.core.data.remote.FcmDataSource
import com.captures2024.soongan.core.data.remote.HomeDataSource
import com.captures2024.soongan.core.data.remote.MembersDataSource
import com.captures2024.soongan.core.data.remote.NotificationsDataSource
import com.captures2024.soongan.core.data.remote.PostLikeDataSource
import com.captures2024.soongan.core.data.remote.ReportDataSource
import com.captures2024.soongan.core.data.remote.WeeklyContestDataSource
import com.captures2024.soongan.core.data.remote.impl.AuthDataSourceImpl
import com.captures2024.soongan.core.data.remote.impl.FcmDataSourceImpl
import com.captures2024.soongan.core.data.remote.impl.HomeDataSourceImpl
import com.captures2024.soongan.core.data.remote.impl.MembersDataSourceImpl
import com.captures2024.soongan.core.data.remote.impl.NotificationsDataSourceImpl
import com.captures2024.soongan.core.data.remote.impl.PostLikeDataSourceImpl
import com.captures2024.soongan.core.data.remote.impl.ReportDataSourceImpl
import com.captures2024.soongan.core.data.remote.impl.WeeklyContestDataSourceImpl
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
    abstract fun bindMembersDataSource(membersDataSourceImpl: MembersDataSourceImpl): MembersDataSource

    @Binds
    @Singleton
    abstract fun bindFcmDataSource(fcmDataSourceImpl: FcmDataSourceImpl): FcmDataSource

    @Binds
    @Singleton
    abstract fun bindAuthDataSource(authDataSourceImpl: AuthDataSourceImpl): AuthDataSource

    @Binds
    @Singleton
    abstract fun bindHomeDataSource(homeDataSourceImpl: HomeDataSourceImpl): HomeDataSource

    @Binds
    @Singleton
    abstract fun bindWeeklyContestDataSource(weeklyContestDataSourceImpl: WeeklyContestDataSourceImpl): WeeklyContestDataSource

    @Binds
    @Singleton
    abstract fun bindReportDataSource(reportDataSourceImpl: ReportDataSourceImpl): ReportDataSource

    @Binds
    @Singleton
    abstract fun bindPostLikeDataSource(postLikeDataSourceImpl: PostLikeDataSourceImpl): PostLikeDataSource

    @Binds
    @Singleton
    abstract fun bindNotificationsDataSource(notificationsDataSourceImpl: NotificationsDataSourceImpl): NotificationsDataSource
}
