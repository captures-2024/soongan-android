package com.captures2024.soongan.domain.usecase.member.impl

import com.captures2024.soongan.data.repository.member.MemberRepository
import com.captures2024.soongan.domain.usecase.member.GetGuestModeFlowUseCase
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetGuestModeFlowUseCaseImpl
@Inject
constructor(
    private val repository: MemberRepository,
) : GetGuestModeFlowUseCase {

    override fun invoke(): StateFlow<Boolean> = repository.isGuestMode
}
