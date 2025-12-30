package com.captures2024.soongan.domain.usecase.member.impl

import com.captures2024.soongan.core.model.dto.UserInfoDto
import com.captures2024.soongan.domain.repository.member.MemberRepository
import com.captures2024.soongan.domain.usecase.member.GetCurrentMemberFlowUseCase
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetCurrentMemberFlowUseCaseImpl
@Inject
constructor(
    private val repository: MemberRepository,
) : GetCurrentMemberFlowUseCase {

    override fun invoke(): StateFlow<UserInfoDto?> = repository.currentMember
}
