package com.captures2024.soongan.core.data.di

import com.captures2024.soongan.core.data.repository.AuthRepository
import com.captures2024.soongan.core.data.repository.DialogRepository
import com.captures2024.soongan.core.data.repository.FcmRepository
import com.captures2024.soongan.core.data.repository.HomeRepository
import com.captures2024.soongan.core.data.repository.LoadingRepository
import com.captures2024.soongan.core.data.repository.MembersRepository
import com.captures2024.soongan.core.data.repository.NotificationsRepository
import com.captures2024.soongan.core.data.repository.PostLikeRepository
import com.captures2024.soongan.core.data.repository.ReportRepository
import com.captures2024.soongan.core.data.repository.SystemRepository
import com.captures2024.soongan.core.data.repository.TokenRepository
import com.captures2024.soongan.core.data.repository.WeeklyContestRepository
import com.captures2024.soongan.core.data.repository.impl.AuthRepositoryImpl
import com.captures2024.soongan.core.data.repository.impl.DialogRepositoryImpl
import com.captures2024.soongan.core.data.repository.impl.FcmRepositoryImpl
import com.captures2024.soongan.core.data.repository.impl.HomeRepositoryImpl
import com.captures2024.soongan.core.data.repository.impl.LoadingRepositoryImpl
import com.captures2024.soongan.core.data.repository.impl.MembersRepositoryImpl
import com.captures2024.soongan.core.data.repository.impl.NotificationsRepositoryImpl
import com.captures2024.soongan.core.data.repository.impl.PostLikeRepositoryImpl
import com.captures2024.soongan.core.data.repository.impl.ReportRepositoryImpl
import com.captures2024.soongan.core.data.repository.impl.SystemRepositoryImpl
import com.captures2024.soongan.core.data.repository.impl.TokenRepositoryImpl
import com.captures2024.soongan.core.data.repository.impl.WeeklyContestRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTokenRepository(tokenRepositoryImpl: TokenRepositoryImpl): TokenRepository

    @Binds
    @Singleton
    abstract fun bindMembersRepository(membersRepositoryImpl: MembersRepositoryImpl): MembersRepository

    @Binds
    @Singleton
    abstract fun bindFcmRepository(fcmRepositoryImpl: FcmRepositoryImpl): FcmRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository

    @Binds
    @Singleton
    abstract fun bindWeeklyContestRepository(weeklyContestRepositoryImpl: WeeklyContestRepositoryImpl): WeeklyContestRepository

    @Binds
    @Singleton
    abstract fun bindLoadingRepository(loadingRepositoryImpl: LoadingRepositoryImpl): LoadingRepository

    @Binds
    @Singleton
    abstract fun bindReportRepository(reportRepositoryImpl: ReportRepositoryImpl): ReportRepository

    @Binds
    @Singleton
    abstract fun bindDialogRepository(dialogRepositoryImpl: DialogRepositoryImpl): DialogRepository

    @Binds
    @Singleton
    abstract fun bindPostLikeRepository(postLikeRepositoryImpl: PostLikeRepositoryImpl): PostLikeRepository

    @Binds
    @Singleton
    abstract fun bindNotificationsRepository(notificationsRepositoryImpl: NotificationsRepositoryImpl): NotificationsRepository

    @Binds
    @Singleton
    abstract fun bindSystemRepository(systemRepositoryImpl: SystemRepositoryImpl): SystemRepository
}
