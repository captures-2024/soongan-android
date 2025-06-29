package com.captures2024.soongan.domain.usecase.member.impl

import com.captures2024.soongan.domain.repository.member.MemberRepository
import com.captures2024.soongan.domain.usecase.member.SetGuestModeUseCase
import javax.inject.Inject

class SetGuestModeUseCaseImpl
@Inject
constructor(
    private val repository: MemberRepository,
) : SetGuestModeUseCase {

    override fun invoke(isGuestMode: Boolean) {
        repository.setGuestMode(isGuestMode)
    }
}
