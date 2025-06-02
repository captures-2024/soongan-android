package com.captures2024.soongan.domain.usecase.member.impl

import com.captures2024.soongan.core.model.dto.UserInfoDto
import com.captures2024.soongan.data.repository.member.MemberRepository
import com.captures2024.soongan.domain.usecase.member.GetMemberInfoUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class GetMemberInfoUseCaseImpl
@Inject
constructor(
    private val repository: MemberRepository,
) : GetMemberInfoUseCase {

    override suspend fun invoke(): Result<UserInfoDto> = runSuspendCatching {
        val userInfoDto = repository.getMemberInfo()

        return@runSuspendCatching userInfoDto
    }
}
