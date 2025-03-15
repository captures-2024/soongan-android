package com.captures2024.soongan.core.domain.usecase.weekly.contests

import com.captures2024.soongan.core.data.repository.WeeklyContestRepository
import com.captures2024.soongan.core.domain.runSuspendCatching
import javax.inject.Inject

class DeletePostUseCase
@Inject
constructor(
    private val weeklyContestRepository: WeeklyContestRepository,
) {

    suspend operator fun invoke(postId: Long): Result<Boolean> = runSuspendCatching {
        return@runSuspendCatching weeklyContestRepository.deletePost(
            postId = postId,
        )
    }
}
