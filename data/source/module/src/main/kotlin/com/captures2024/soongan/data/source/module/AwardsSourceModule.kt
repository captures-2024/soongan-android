package com.captures2024.soongan.data.source.module

import com.captures2024.soongan.data.source.awards.impl.remote.AwardsRemoteDataSourceImpl
import com.captures2024.soongan.data.source.awards.remote.AwardsRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class AwardsSourceModule {

    @Binds
    @Singleton
    abstract fun bindAwardsRemoteDataSource(awardsRemoteDataSourceImpl: AwardsRemoteDataSourceImpl): AwardsRemoteDataSource
}
