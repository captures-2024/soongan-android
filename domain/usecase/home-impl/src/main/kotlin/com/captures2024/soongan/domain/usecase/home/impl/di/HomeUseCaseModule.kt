package com.captures2024.soongan.domain.usecase.home.impl.di

import com.captures2024.soongan.domain.usecase.home.GetHomeUseCase
import com.captures2024.soongan.domain.usecase.home.impl.GetHomeUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class HomeUseCaseModule {

    @Binds
    abstract fun bindGetHomeUseCase(getHomeUseCaseImpl: GetHomeUseCaseImpl): GetHomeUseCase
}
