package com.captures2024.soongan.data.source.module.contest

import com.captures2024.soongan.data.source.contest.impl.local.ContentVisibilityLocalDataSourceImpl
import com.captures2024.soongan.data.source.contest.impl.remote.PostLikeRemoteDataSourceImpl
import com.captures2024.soongan.data.source.contest.impl.remote.WeeklyContestRemoteDataSourceImpl
import com.captures2024.soongan.data.source.contest.local.ContentVisibilityLocalDataSource
import com.captures2024.soongan.data.source.contest.remote.PostLikeRemoteDataSource
import com.captures2024.soongan.data.source.contest.remote.WeeklyContestRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ContestSourceModule {

    @Binds
    @Singleton
    abstract fun bindPostLikeRemoteDataSource(postLikeRemoteDataSourceImpl: PostLikeRemoteDataSourceImpl): PostLikeRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindWeeklyContestRemoteDataSource(weeklyContestRemoteDataSourceImpl: WeeklyContestRemoteDataSourceImpl): WeeklyContestRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindContentVisibilityLocalDataSource(contentVisibilityLocalDataSourceImpl: ContentVisibilityLocalDataSourceImpl): ContentVisibilityLocalDataSource
}
