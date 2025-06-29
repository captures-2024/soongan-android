package com.captures2024.soongan.domain.usecase.module

import com.captures2024.soongan.domain.usecase.auth.ReissueTokenUseCase
import com.captures2024.soongan.domain.usecase.auth.SignOutSocialPlatformUseCase
import com.captures2024.soongan.domain.usecase.auth.SigningGoogleUseCase
import com.captures2024.soongan.domain.usecase.auth.SigningKakaoUseCase
import com.captures2024.soongan.domain.usecase.auth.WithdrawMemberUseCase
import com.captures2024.soongan.domain.usecase.auth.impl.ReissueTokenUseCaseImpl
import com.captures2024.soongan.domain.usecase.auth.impl.SignOutSocialPlatformUseCaseImpl
import com.captures2024.soongan.domain.usecase.auth.impl.SigningGoogleUseCaseImpl
import com.captures2024.soongan.domain.usecase.auth.impl.SigningKakaoUseCaseImpl
import com.captures2024.soongan.domain.usecase.auth.impl.WithdrawMemberUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class AuthUseCaseModule {

    @Binds
    abstract fun bindReissueTokenUseCase(reissueTokenUseCaseImpl: ReissueTokenUseCaseImpl): ReissueTokenUseCase

    @Binds
    abstract fun bindSigningGoogleUseCase(signingGoogleUseCaseImpl: SigningGoogleUseCaseImpl): SigningGoogleUseCase

    @Binds
    abstract fun bindSigningKakaoUseCase(signingKakaoUseCaseImpl: SigningKakaoUseCaseImpl): SigningKakaoUseCase

    @Binds
    abstract fun bindSignOutSocialPlatformUseCase(signOutSocialPlatformUseCaseImpl: SignOutSocialPlatformUseCaseImpl): SignOutSocialPlatformUseCase

    @Binds
    abstract fun bindWithdrawMemberUseCase(withdrawMemberUseCaseImpl: WithdrawMemberUseCaseImpl): WithdrawMemberUseCase
}
