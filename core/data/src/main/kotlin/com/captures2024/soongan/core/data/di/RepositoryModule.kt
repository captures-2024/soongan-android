package com.captures2024.soongan.core.data.di

import com.captures2024.soongan.core.data.repository.MembersRepositoryImpl
import com.captures2024.soongan.core.data.repository.TokenRepositoryImpl
import com.captures2024.soongan.core.domain.repository.MembersRepository
import com.captures2024.soongan.core.domain.repository.TokenRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTokenRepository(tokenRepositoryImpl: TokenRepositoryImpl): TokenRepository

    @Binds
    @Singleton
    abstract fun bindMembersRepository(membersRepositoryImpl: MembersRepositoryImpl): MembersRepository

}