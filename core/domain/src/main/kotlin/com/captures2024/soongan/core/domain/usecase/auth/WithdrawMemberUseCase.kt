package com.captures2024.soongan.core.domain.usecase.auth

import com.captures2024.soongan.core.data.repository.AuthRepository
import javax.inject.Inject

class WithdrawMemberUseCase
@Inject
constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(): Result<Boolean> = runCatching {
        val resultConditionDto = authRepository.withdrawMember()

        return@runCatching resultConditionDto.result
    }
}
