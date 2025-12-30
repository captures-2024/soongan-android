package com.captures2024.soongan.domain.usecase.member.impl

import com.captures2024.soongan.domain.repository.member.MemberRepository
import com.captures2024.soongan.domain.usecase.member.ClearCurrentMemberUseCase
import javax.inject.Inject

class ClearCurrentMemberUseCaseImpl
@Inject
constructor(
    private val memberRepository: MemberRepository,
) : ClearCurrentMemberUseCase {

    override suspend fun invoke() {
        memberRepository.clearCurrentMember()
    }
}
