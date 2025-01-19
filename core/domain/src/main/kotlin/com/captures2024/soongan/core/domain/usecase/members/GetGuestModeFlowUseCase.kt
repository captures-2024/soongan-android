package com.captures2024.soongan.core.domain.usecase.members

import com.captures2024.soongan.core.data.repository.MembersRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetGuestModeFlowUseCase
@Inject
constructor(
    private val repository: MembersRepository,
) {

    operator fun invoke(): StateFlow<Boolean> = repository.isGuestMode
}