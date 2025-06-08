package com.captures2024.soongan.domain.usecase.contest.impl

import com.captures2024.soongan.core.model.dto.PostLikeDto
import com.captures2024.soongan.data.repository.contest.PostLikeRepository
import com.captures2024.soongan.domain.usecase.contest.PutPostLikeUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class PutPostLikeUseCaseImpl
@Inject
constructor(
    private val repository: PostLikeRepository,
) : PutPostLikeUseCase {

    override suspend fun invoke(
        postId: Long,
        contestType: String,
    ): Result<PostLikeDto> = runSuspendCatching {
        val resultConditionDto = repository.putPostLike(
            postId = postId,
            contestType = contestType,
        )

        return@runSuspendCatching resultConditionDto
    }
}
