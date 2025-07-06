package com.captures2024.soongan.data.repository.module

import com.captures2024.soongan.data.repository.awards.AwardsRepository
import com.captures2024.soongan.data.repository.awards.impl.AwardsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class AwardsRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAwardsRepository(awardsRepositoryImpl: AwardsRepositoryImpl): AwardsRepository
}
