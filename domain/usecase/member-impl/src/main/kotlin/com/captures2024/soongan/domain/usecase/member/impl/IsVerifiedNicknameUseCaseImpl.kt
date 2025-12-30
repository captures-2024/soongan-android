package com.captures2024.soongan.domain.usecase.member.impl

import com.captures2024.soongan.domain.repository.member.MemberRepository
import com.captures2024.soongan.domain.usecase.member.IsVerifiedNicknameUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class IsVerifiedNicknameUseCaseImpl
@Inject
constructor(
    private val repository: MemberRepository,
) : IsVerifiedNicknameUseCase {

    override suspend fun invoke(nickname: String): Result<Boolean> = runSuspendCatching {
        val resultConditionDto = repository.isVerifiedNickname(nickname = nickname)

        return@runSuspendCatching resultConditionDto.result
    }
}
