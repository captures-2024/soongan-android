package com.captures2024.soongan.core.domain.usecase.auth

import com.captures2024.soongan.core.data.repository.AuthRepository
import javax.inject.Inject

class SignOutSocialPlatformUseCase
@Inject
constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(): Result<Boolean> = runCatching {
        val resultConditionDto = authRepository.signOutSocialPlatform()

        return@runCatching resultConditionDto.result
    }
}
