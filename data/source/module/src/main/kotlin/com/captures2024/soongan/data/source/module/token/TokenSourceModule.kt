package com.captures2024.soongan.data.source.module.token

import com.captures2024.soongan.data.source.token.impl.local.TokenLocalDataSourceImpl
import com.captures2024.soongan.data.source.token.local.TokenLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class TokenSourceModule {

    @Binds
    @Singleton
    abstract fun bindTokenLocalDataSource(tokenLocalDataSourceImpl: TokenLocalDataSourceImpl): TokenLocalDataSource
}