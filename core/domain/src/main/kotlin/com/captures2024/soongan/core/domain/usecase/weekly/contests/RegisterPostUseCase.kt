package com.captures2024.soongan.core.domain.usecase.weekly.contests

import com.captures2024.soongan.core.data.repository.WeeklyContestRepository
import com.captures2024.soongan.core.domain.runSuspendCatching
import javax.inject.Inject

class RegisterPostUseCase
@Inject
constructor(
    private val weeklyContestRepository: WeeklyContestRepository
) {

    suspend operator fun invoke(params: Params): Result<Long> = runSuspendCatching {
        val result = weeklyContestRepository.registerPost(
            title = params.title,
            imageFile = params.imageFile,
        )

        return@runSuspendCatching result.postId
    }


    data class Params(
        val title: String,
        val imageFile: String,
    )
}