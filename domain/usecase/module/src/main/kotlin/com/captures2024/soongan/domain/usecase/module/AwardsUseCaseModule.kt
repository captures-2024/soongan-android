package com.captures2024.soongan.domain.usecase.module

import com.captures2024.soongan.domain.usecase.awards.GetAwardsInfoUseCase
import com.captures2024.soongan.domain.usecase.awards.GetAwardsListUseCase
import com.captures2024.soongan.domain.usecase.awards.impl.GetAwardsInfoUseCaseImpl
import com.captures2024.soongan.domain.usecase.awards.impl.GetAwardsListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class AwardsUseCaseModule {

    @Binds
    abstract fun bindGetAwardsListUseCase(getAwardsListUseCaseImpl: GetAwardsListUseCaseImpl): GetAwardsListUseCase

    @Binds
    abstract fun bindGetAwardsInfoUseCase(getAwardsInfoUseCaseImpl: GetAwardsInfoUseCaseImpl): GetAwardsInfoUseCase
}
