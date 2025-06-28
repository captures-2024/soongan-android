package com.captures2024.soongan.data.repository.module

import com.captures2024.soongan.data.repository.contest.impl.ContentVisibilityRepositoryImpl
import com.captures2024.soongan.data.repository.contest.impl.PostLikeRepositoryImpl
import com.captures2024.soongan.data.repository.contest.impl.WeeklyContestRepositoryImpl
import com.captures2024.soongan.domain.repository.contest.ContentVisibilityRepository
import com.captures2024.soongan.domain.repository.contest.PostLikeRepository
import com.captures2024.soongan.domain.repository.contest.WeeklyContestRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ContestRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPostLikeRepository(postLikeRepositoryImpl: PostLikeRepositoryImpl): PostLikeRepository

    @Binds
    @Singleton
    abstract fun bindWeeklyContestRepository(weeklyContestRepositoryImpl: WeeklyContestRepositoryImpl): WeeklyContestRepository

    @Binds
    @Singleton
    abstract fun bindContentVisibilityRepository(contentVisibilityRepositoryImpl: ContentVisibilityRepositoryImpl): ContentVisibilityRepository
}
