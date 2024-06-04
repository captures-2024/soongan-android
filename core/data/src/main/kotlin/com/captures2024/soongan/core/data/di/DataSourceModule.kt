package com.captures2024.soongan.core.data.di

import com.captures2024.soongan.core.datastore.TokenDataSource
import com.captures2024.soongan.core.datastore.TokenDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindTokenDataSource(tokenDataSourceImpl: TokenDataSourceImpl): TokenDataSource

}