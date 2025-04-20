package com.captures2024.soongan.core.data.di

import com.captures2024.soongan.core.data.source.fcm.local.FcmLocalDataSource
import com.captures2024.soongan.core.data.source.fcm.local.FcmLocalDataSourceImpl
import com.captures2024.soongan.core.datastore.TokenDataSource
import com.captures2024.soongan.core.datastore.TokenDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindTokenDataSource(tokenDataSourceImpl: TokenDataSourceImpl): TokenDataSource

    @Binds
    @Singleton
    abstract fun bindFcmLocalDataSource(fcmLocalDataSourceImpl: FcmLocalDataSourceImpl): FcmLocalDataSource
}
