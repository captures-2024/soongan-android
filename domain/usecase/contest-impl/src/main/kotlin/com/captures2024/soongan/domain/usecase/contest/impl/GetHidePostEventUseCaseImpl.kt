package com.captures2024.soongan.domain.usecase.contest.impl

import com.captures2024.soongan.domain.repository.contest.ContentVisibilityRepository
import com.captures2024.soongan.domain.usecase.contest.GetHidePostEventUseCase
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class GetHidePostEventUseCaseImpl
@Inject
constructor(
    private val repository: ContentVisibilityRepository,
) : GetHidePostEventUseCase {

    override fun invoke(): SharedFlow<Long> = repository.hidePostEvent
}
