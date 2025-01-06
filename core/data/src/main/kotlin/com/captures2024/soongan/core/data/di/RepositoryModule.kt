package com.captures2024.soongan.core.data.di

import com.captures2024.soongan.core.data.repository.AuthRepository
import com.captures2024.soongan.core.data.repository.FcmRepository
import com.captures2024.soongan.core.data.repository.HomeRepository
import com.captures2024.soongan.core.data.repository.MembersRepository
import com.captures2024.soongan.core.data.repository.TokenRepository
import com.captures2024.soongan.core.data.repository.impl.AuthRepositoryImpl
import com.captures2024.soongan.core.data.repository.impl.FcmRepositoryImpl
import com.captures2024.soongan.core.data.repository.impl.HomeRepositoryImpl
import com.captures2024.soongan.core.data.repository.impl.MembersRepositoryImpl
import com.captures2024.soongan.core.data.repository.impl.TokenRepositoryImpl
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

    @Binds
    @Singleton
    abstract fun bindFcmRepository(fcmRepositoryImpl: FcmRepositoryImpl): FcmRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository
}
