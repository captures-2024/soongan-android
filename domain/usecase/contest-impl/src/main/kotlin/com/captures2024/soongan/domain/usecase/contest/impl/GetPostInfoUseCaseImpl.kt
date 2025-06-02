package com.captures2024.soongan.domain.usecase.contest.impl

import com.captures2024.soongan.core.model.dto.PostInfoDto
import com.captures2024.soongan.data.repository.contest.WeeklyContestRepository
import com.captures2024.soongan.domain.usecase.contest.GetPostInfoUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class GetPostInfoUseCaseImpl
@Inject
constructor(
    private val weeklyContestRepository: WeeklyContestRepository,
) : GetPostInfoUseCase {

    override suspend fun invoke(postId: Long): Result<PostInfoDto> = runSuspendCatching {
        return@runSuspendCatching weeklyContestRepository.getPostInfo(postId)
    }
}
