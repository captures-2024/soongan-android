package com.captures2024.soongan.core.domain.usecase.members

import com.captures2024.soongan.core.data.repository.MembersRepository
import com.captures2024.soongan.core.domain.runSuspendCatching
import javax.inject.Inject

class IsVerifiedNicknameUseCase
@Inject
constructor(
    private val membersRepository: MembersRepository,
) {

    suspend operator fun invoke(
        nickname: String,
    ): Result<Boolean> = runSuspendCatching {
        val resultConditionDto = membersRepository.isVerifiedNickname(nickname = nickname)

        return@runSuspendCatching resultConditionDto.result
    }
}
