package com.captures2024.soongan.core.data.repository.impl

import com.captures2024.soongan.core.data.remote.PostLikeDataSource
import com.captures2024.soongan.core.data.repository.PostLikeRepository
import com.captures2024.soongan.core.model.dto.ResultConditionDto
import javax.inject.Inject

class PostLikeRepositoryImpl
@Inject constructor(
    private val dataSource: PostLikeDataSource,
) : PostLikeRepository {
    override suspend fun putPostLike(
        postId: Long,
        contestType: String,
    ): ResultConditionDto {
        val data = dataSource.putPostLike(
            postId = postId,
            contestType = contestType
        )

        if (data == null) {
            throw NullPointerException("postLikeDto(PUT) is null")
        }

        if (postId != data.postId) {
            return ResultConditionDto(false)
        }

        return ResultConditionDto(true)
    }

    override suspend fun deletePostLike(
        postId: Long,
        contestType: String,
    ): ResultConditionDto {
        val data = dataSource.deletePostLike(
            postId = postId,
            contestType = contestType
        )

        if (data == null) {
            throw NullPointerException("postLikeDto(DELETE) is null")
        }

        if (postId != data.postId) {
            return ResultConditionDto(false)
        }

        return ResultConditionDto(true)
    }
}