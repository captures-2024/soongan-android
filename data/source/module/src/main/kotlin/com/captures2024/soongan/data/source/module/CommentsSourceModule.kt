package com.captures2024.soongan.data.source.module

import com.captures2024.soongan.data.source.comments.CommentsRemoteDataSource
import com.captures2024.soongan.data.source.comments.impl.CommentsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class CommentsSourceModule {

    @Binds
    @Singleton
    abstract fun bindCommentsRemoteDataSource(commentsRemoteDataSourceImpl: CommentsRemoteDataSourceImpl): CommentsRemoteDataSource
}
