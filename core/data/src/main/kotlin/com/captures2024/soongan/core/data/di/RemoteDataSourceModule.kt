package com.captures2024.soongan.core.data.di

import com.captures2024.soongan.core.data.remote.AuthDataSource
import com.captures2024.soongan.core.data.remote.FcmDataSource
import com.captures2024.soongan.core.data.remote.HomeDataSource
import com.captures2024.soongan.core.data.remote.MembersDataSource
import com.captures2024.soongan.core.data.remote.impl.AuthDataSourceImpl
import com.captures2024.soongan.core.data.remote.impl.FcmDataSourceImpl
import com.captures2024.soongan.core.data.remote.impl.HomeDataSourceImpl
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

    @Binds
    @Singleton
    abstract fun bindAuthDataSource(authDataSourceImpl: AuthDataSourceImpl): AuthDataSource

    @Binds
    @Singleton
    abstract fun bindHomeDataSource(homeDataSourceImpl: HomeDataSourceImpl): HomeDataSource
}
