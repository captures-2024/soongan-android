package com.captures2024.soongan.domain.usecase.contest.impl

import com.captures2024.soongan.data.repository.contest.WeeklyContestRepository
import com.captures2024.soongan.domain.usecase.contest.RegisterPostUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class RegisterPostUseCaseImpl
@Inject
constructor(
    private val weeklyContestRepository: WeeklyContestRepository,
) : RegisterPostUseCase {

    override suspend fun invoke(
        title: String,
        imageFile: String,
    ): Result<Long> = runSuspendCatching {
        val result = weeklyContestRepository.registerPost(
            title = title,
            imageFile = imageFile,
        )

        return@runSuspendCatching result.postId
    }
}
