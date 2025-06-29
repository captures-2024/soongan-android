package com.captures2024.soongan.domain.usecase.contest.impl

import com.captures2024.soongan.core.model.dto.PostLikeDto
import com.captures2024.soongan.domain.repository.contest.PostLikeRepository
import com.captures2024.soongan.domain.usecase.contest.DeletePostLikeUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class DeletePostLikeUseCaseImpl
@Inject
constructor(
    private val repository: PostLikeRepository,
) : DeletePostLikeUseCase {

    override suspend operator fun invoke(
        postId: Long,
        contestType: String,
    ): Result<PostLikeDto> = runSuspendCatching {
        val resultConditionDto = repository.deletePostLike(
            postId = postId,
            contestType = contestType,
        )

        return@runSuspendCatching resultConditionDto
    }
}
