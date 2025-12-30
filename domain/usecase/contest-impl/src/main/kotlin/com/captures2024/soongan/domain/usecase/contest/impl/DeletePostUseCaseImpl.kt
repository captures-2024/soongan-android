package com.captures2024.soongan.domain.usecase.contest.impl

import com.captures2024.soongan.domain.repository.contest.WeeklyContestRepository
import com.captures2024.soongan.domain.usecase.contest.DeletePostUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class DeletePostUseCaseImpl
@Inject
constructor(
    private val weeklyContestRepository: WeeklyContestRepository,
) : DeletePostUseCase {

    override suspend operator fun invoke(postId: Long): Result<Boolean> = runSuspendCatching {
        return@runSuspendCatching weeklyContestRepository.deletePost(
            postId = postId,
        )
    }
}
