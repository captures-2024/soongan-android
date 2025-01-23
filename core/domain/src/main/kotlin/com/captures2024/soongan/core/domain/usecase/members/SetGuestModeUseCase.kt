package com.captures2024.soongan.core.domain.usecase.members

import com.captures2024.soongan.core.data.repository.MembersRepository
import com.captures2024.soongan.core.domain.runSuspendCatching
import javax.inject.Inject

class SetGuestModeUseCase
@Inject
constructor(
    private val repository: MembersRepository,
) {

    suspend operator fun invoke(isGuestMode: Boolean) = runSuspendCatching {
        repository.setGuestMode(isGuestMode)
    }
}