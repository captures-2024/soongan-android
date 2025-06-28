package com.captures2024.soongan.data.source.module.home

import com.captures2024.soongan.data.source.home.impl.remote.HomeRemoteDataSourceImpl
import com.captures2024.soongan.data.source.home.remote.HomeRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class HomeSourceModule {

    @Binds
    @Singleton
    abstract fun bindHomeRemoteDataSource(homeRemoteDataSourceImpl: HomeRemoteDataSourceImpl): HomeRemoteDataSource
}
