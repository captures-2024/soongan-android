package com.captures2024.soongan.data.source.contest.impl.di

import com.captures2024.soongan.data.source.contest.impl.remote.PostLikeRemoteDataSourceImpl
import com.captures2024.soongan.data.source.contest.impl.remote.WeeklyContestRemoteDataSourceImpl
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
}
