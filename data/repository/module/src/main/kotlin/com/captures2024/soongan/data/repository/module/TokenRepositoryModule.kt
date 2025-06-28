package com.captures2024.soongan.data.repository.module

import com.captures2024.soongan.data.repository.token.impl.TokenRepositoryImpl
import com.captures2024.soongan.domain.repository.token.TokenRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class TokenRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTokenRepository(tokenRepositoryImpl: TokenRepositoryImpl): TokenRepository
}