package com.captures2024.soongan.core.domain.usecase.weekly.contests

import com.captures2024.soongan.core.data.repository.WeeklyContestRepository
import com.captures2024.soongan.core.domain.runSuspendCatching
import com.captures2024.soongan.core.model.dto.PostInfoDto
import javax.inject.Inject

class GetPostInfoUseCase
@Inject
constructor(
    private val weeklyContestRepository: WeeklyContestRepository
) {

    suspend operator fun invoke(postId: Long): Result<PostInfoDto> = runSuspendCatching {
        return@runSuspendCatching weeklyContestRepository.getPostInfo(postId)
    }
}