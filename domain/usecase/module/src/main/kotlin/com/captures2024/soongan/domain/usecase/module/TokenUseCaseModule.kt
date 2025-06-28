package com.captures2024.soongan.domain.usecase.module

import com.captures2024.soongan.domain.usecase.token.ClearAccessTokenUseCase
import com.captures2024.soongan.domain.usecase.token.ClearAllTokenUseCase
import com.captures2024.soongan.domain.usecase.token.ClearRefreshTokenUseCase
import com.captures2024.soongan.domain.usecase.token.GetAccessTokenUseCase
import com.captures2024.soongan.domain.usecase.token.GetAllTokenUseCase
import com.captures2024.soongan.domain.usecase.token.GetRefreshTokenUseCase
import com.captures2024.soongan.domain.usecase.token.GetUUIDUseCase
import com.captures2024.soongan.domain.usecase.token.SetAllTokenUseCase
import com.captures2024.soongan.domain.usecase.token.SetRefreshTokenUseCase
import com.captures2024.soongan.domain.usecase.token.SetUUIDUseCase
import com.captures2024.soongan.domain.usecase.token.impl.ClearAccessTokenUseCaseImpl
import com.captures2024.soongan.domain.usecase.token.impl.ClearAllTokenUseCaseImpl
import com.captures2024.soongan.domain.usecase.token.impl.ClearRefreshTokenUseCaseImpl
import com.captures2024.soongan.domain.usecase.token.impl.GetAccessTokenUseCaseImpl
import com.captures2024.soongan.domain.usecase.token.impl.GetAllTokenUseCaseImpl
import com.captures2024.soongan.domain.usecase.token.impl.GetRefreshTokenUseCaseImpl
import com.captures2024.soongan.domain.usecase.token.impl.GetUUIDUseCaseImpl
import com.captures2024.soongan.domain.usecase.token.impl.SetAllTokenUseCaseImpl
import com.captures2024.soongan.domain.usecase.token.impl.SetRefreshTokenUseCaseImpl
import com.captures2024.soongan.domain.usecase.token.impl.SetUUIDUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class TokenUseCaseModule {

    @Binds
    abstract fun bindClearAccessTokenUseCase(clearAccessTokenUseCaseImpl: ClearAccessTokenUseCaseImpl): ClearAccessTokenUseCase

    @Binds
    abstract fun bindClearAllTokenUseCase(clearAllTokenUseCaseImpl: ClearAllTokenUseCaseImpl): ClearAllTokenUseCase

    @Binds
    abstract fun bindClearRefreshTokenUseCase(clearRefreshTokenUseCaseImpl: ClearRefreshTokenUseCaseImpl): ClearRefreshTokenUseCase

    @Binds
    abstract fun bindGetAccessTokenUseCase(getAccessTokenUseCaseImpl: GetAccessTokenUseCaseImpl): GetAccessTokenUseCase

    @Binds
    abstract fun bindGetAllTokenUseCase(getAllTokenUseCaseImpl: GetAllTokenUseCaseImpl): GetAllTokenUseCase

    @Binds
    abstract fun bindGetRefreshTokenUseCase(getRefreshTokenUseCaseImpl: GetRefreshTokenUseCaseImpl): GetRefreshTokenUseCase

    @Binds
    abstract fun bindGetUUIDUseCase(getUUIDUseCaseImpl: GetUUIDUseCaseImpl): GetUUIDUseCase

    @Binds
    abstract fun bindSetAllTokenUseCase(setAllTokenUseCaseImpl: SetAllTokenUseCaseImpl): SetAllTokenUseCase

    @Binds
    abstract fun bindSetRefreshTokenUseCase(setRefreshTokenUseCaseImpl: SetRefreshTokenUseCaseImpl): SetRefreshTokenUseCase

    @Binds
    abstract fun bindSetUUIDUseCase(setUUIDUseCaseImpl: SetUUIDUseCaseImpl): SetUUIDUseCase
}