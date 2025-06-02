package com.captures2024.soongan.domain.usecase.member.impl

import com.captures2024.soongan.data.repository.member.MemberRepository
import com.captures2024.soongan.domain.usecase.member.GetIsCurrentGuestModeUseCase
import javax.inject.Inject

class GetIsCurrentGuestModeUseCaseImpl
@Inject
constructor(
    private val repository: MemberRepository,
) : GetIsCurrentGuestModeUseCase {

    override fun invoke(): Boolean = repository.isGuestMode.value
}
