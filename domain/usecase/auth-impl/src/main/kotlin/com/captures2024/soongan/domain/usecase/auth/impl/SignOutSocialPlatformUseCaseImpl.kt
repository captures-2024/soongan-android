package com.captures2024.soongan.domain.usecase.auth.impl

import com.captures2024.soongan.data.repository.auth.AuthRepository
import com.captures2024.soongan.domain.usecase.auth.SignOutSocialPlatformUseCase
import javax.inject.Inject

class SignOutSocialPlatformUseCaseImpl
@Inject
constructor(
    private val authRepository: AuthRepository,
) : SignOutSocialPlatformUseCase {
    override suspend operator fun invoke(): Result<Boolean> = runCatching {
        val resultConditionDto = authRepository.signOutSocialPlatform()

        return@runCatching resultConditionDto.result
    }
}
