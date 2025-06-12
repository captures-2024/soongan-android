package com.captures2024.soongan.domain.usecase.contest.impl

import com.captures2024.soongan.data.repository.contest.ContentVisibilityRepository
import com.captures2024.soongan.domain.usecase.contest.GetHideCommentEventUseCase
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class GetHideCommentEventUseCaseImpl
@Inject
constructor(
    private val repository: ContentVisibilityRepository,
) : GetHideCommentEventUseCase {

    override fun invoke(): SharedFlow<Long> = repository.hideCommentEvent
}
