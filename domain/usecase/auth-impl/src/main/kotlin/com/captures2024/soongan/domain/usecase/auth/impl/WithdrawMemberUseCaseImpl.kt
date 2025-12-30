package com.captures2024.soongan.domain.usecase.auth.impl

import com.captures2024.soongan.domain.repository.auth.AuthRepository
import com.captures2024.soongan.domain.usecase.auth.WithdrawMemberUseCase
import javax.inject.Inject

class WithdrawMemberUseCaseImpl
@Inject
constructor(
    private val authRepository: AuthRepository,
) : WithdrawMemberUseCase {

    override suspend operator fun invoke(): Result<Boolean> = runCatching {
        val resultConditionDto = authRepository.withdrawMember()

        return@runCatching resultConditionDto.result
    }
}
