package com.captures2024.soongan.domain.usecase.contest.impl

import com.captures2024.soongan.data.repository.contest.WeeklyContestRepository
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class EditPostTitleUseCaseImpl
@Inject
constructor(
    private val weeklyContestRepository: WeeklyContestRepository,
) {

    suspend operator fun invoke(
        postId: Long,
        title: String,
    ): Result<String> = runSuspendCatching {
        return@runSuspendCatching weeklyContestRepository.editPostTitle(
            postId = postId,
            title = title,
        )
    }
}
