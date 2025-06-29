package com.captures2024.soongan.data.source.module

import com.captures2024.soongan.data.source.fcm.impl.local.FcmLocalDataSourceImpl
import com.captures2024.soongan.data.source.fcm.impl.remote.FcmRemoteDataSourceImpl
import com.captures2024.soongan.data.source.fcm.local.FcmLocalDataSource
import com.captures2024.soongan.data.source.fcm.remote.FcmRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class FcmSourceModule {

    @Binds
    @Singleton
    abstract fun bindFcmLocalDataSource(fcmLocalDataSourceImpl: FcmLocalDataSourceImpl): FcmLocalDataSource

    @Binds
    @Singleton
    abstract fun bindFcmRemoteDataSource(fcmRemoteDataSourceImpl: FcmRemoteDataSourceImpl): FcmRemoteDataSource
}
