package com.captures2024.soongan.core.domain.usecase.like

import com.captures2024.soongan.core.data.repository.PostLikeRepository
import com.captures2024.soongan.core.domain.runSuspendCatching
import javax.inject.Inject

class DeletePostLikeUseCase
@Inject
constructor(
    private val repository: PostLikeRepository,
) {

    suspend operator fun invoke(postId: Long, contestType: String): Result<Boolean> = runSuspendCatching {
        val resultConditionDto = repository.deletePostLike(
            postId = postId, contestType = contestType
        )

        return@runSuspendCatching resultConditionDto.result
    }
}