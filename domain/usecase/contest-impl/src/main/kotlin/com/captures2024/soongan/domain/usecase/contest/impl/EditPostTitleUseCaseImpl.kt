package com.captures2024.soongan.domain.usecase.contest.impl

import com.captures2024.soongan.domain.repository.contest.WeeklyContestRepository
import com.captures2024.soongan.domain.usecase.contest.EditPostTitleUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class EditPostTitleUseCaseImpl
@Inject
constructor(
    private val weeklyContestRepository: WeeklyContestRepository,
) : EditPostTitleUseCase {

    override suspend fun invoke(
        postId: Long,
        title: String,
    ): Result<String> = runSuspendCatching {
        return@runSuspendCatching weeklyContestRepository.editPostTitle(
            postId = postId,
            title = title,
        )
    }
}
