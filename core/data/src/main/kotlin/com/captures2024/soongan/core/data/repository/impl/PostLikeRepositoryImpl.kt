package com.captures2024.soongan.core.data.repository.impl

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.data.source.post_like.remote.PostLikeRemoteDataSource
import com.captures2024.soongan.core.data.repository.PostLikeRepository
import com.captures2024.soongan.core.model.dto.PostLikeDto
import javax.inject.Inject

class PostLikeRepositoryImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val dataSource: PostLikeRemoteDataSource,
) : PostLikeRepository {

    init {
        analyticsHelper.d { "PostLikeRepository:init" }
    }

    override suspend fun putPostLike(
        postId: Long,
        contestType: String,
    ): PostLikeDto {
        val data = dataSource.putPostLike(
            postId = postId,
            contestType = contestType,
        )

        if (data == null) {
            throw NullPointerException("postLikeDto(PUT) is null")
        }

        return data
    }

    override suspend fun deletePostLike(
        postId: Long,
        contestType: String,
    ): PostLikeDto {
        val data = dataSource.deletePostLike(
            postId = postId,
            contestType = contestType,
        )

        if (data == null) {
            throw NullPointerException("postLikeDto(DELETE) is null")
        }

        return data
    }
}
