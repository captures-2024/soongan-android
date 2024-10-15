package com.captures2024.soongan.core.data.di

import com.captures2024.soongan.core.data.remote.FcmDataSource
import com.captures2024.soongan.core.data.remote.MembersDataSource
import com.captures2024.soongan.core.data.remote.impl.FcmDataSourceImpl
import com.captures2024.soongan.core.data.remote.impl.MembersDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindMembersDataSource(membersDataSourceImpl: MembersDataSourceImpl): MembersDataSource

    @Binds
    @Singleton
    abstract fun bindFcmDataSource(fcmDataSourceImpl: FcmDataSourceImpl): FcmDataSource
}
