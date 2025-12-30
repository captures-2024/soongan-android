package com.captures2024.soongan.data.source.module

import com.captures2024.soongan.data.source.auth.impl.remote.AuthRemoteDataSourceImpl
import com.captures2024.soongan.data.source.auth.remote.AuthRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class AuthSourceModule {

    @Binds
    @Singleton
    abstract fun bindAuthRemoteDataSource(authRemoteDataSourceImpl: AuthRemoteDataSourceImpl): AuthRemoteDataSource
}
