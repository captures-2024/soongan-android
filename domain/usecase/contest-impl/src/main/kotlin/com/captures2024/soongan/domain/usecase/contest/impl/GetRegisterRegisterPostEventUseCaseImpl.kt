package com.captures2024.soongan.domain.usecase.contest.impl

import com.captures2024.soongan.domain.repository.contest.ContentVisibilityRepository
import com.captures2024.soongan.domain.usecase.contest.GetRegisterPostEventUseCase
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class GetRegisterRegisterPostEventUseCaseImpl
@Inject
constructor(
    private val repository: ContentVisibilityRepository,
) : GetRegisterPostEventUseCase {

    override fun invoke(): SharedFlow<Unit> = repository.registerPostEvent
}
