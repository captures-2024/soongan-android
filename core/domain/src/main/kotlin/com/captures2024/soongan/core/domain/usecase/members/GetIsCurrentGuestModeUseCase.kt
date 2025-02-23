package com.captures2024.soongan.core.domain.usecase.members

import com.captures2024.soongan.core.data.repository.MembersRepository
import javax.inject.Inject

class GetIsCurrentGuestModeUseCase
@Inject
constructor(
    private val repository: MembersRepository,
) {

    operator fun invoke(): Boolean = repository.isGuestMode.value
}
