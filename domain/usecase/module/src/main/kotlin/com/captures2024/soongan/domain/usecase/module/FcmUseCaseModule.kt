package com.captures2024.soongan.domain.usecase.module

import com.captures2024.soongan.domain.usecase.fcm.GetFcmUseCase
import com.captures2024.soongan.domain.usecase.fcm.InitFcmUseCase
import com.captures2024.soongan.domain.usecase.fcm.impl.GetFcmUseCaseImpl
import com.captures2024.soongan.domain.usecase.fcm.impl.InitFcmUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class FcmUseCaseModule {

    @Binds
    abstract fun bindGetFcmUseCase(getFcmUseCaseImpl: GetFcmUseCaseImpl): GetFcmUseCase

    @Binds
    abstract fun bindInitFcmUseCase(initFcmUseCaseImpl: InitFcmUseCaseImpl): InitFcmUseCase
}
