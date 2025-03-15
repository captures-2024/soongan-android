package com.captures2024.soongan.core.data.remote.impl

import com.captures2024.soongan.core.data.mapper.toPostLikeDto
import com.captures2024.soongan.core.data.remote.PostLikeDataSource
import com.captures2024.soongan.core.data.service.PostLikeService
import com.captures2024.soongan.core.data.utils.safeAPICall
import com.captures2024.soongan.core.model.dto.PostLikeDto
import com.captures2024.soongan.core.model.network.request.like.PostLikeRequest
import javax.inject.Inject

class PostLikeDataSourceImpl
@Inject constructor(
    private val postLikeService: PostLikeService,
) : PostLikeDataSource {

    override suspend fun putPostLike(
        postId: Long,
        contestType: String,
    ): PostLikeDto? = safeAPICall {
        postLikeService.putPostLike(
            request = PostLikeRequest(postId = postId, contestType = contestType)
        )
    }.body?.responseData?.toPostLikeDto()

    override suspend fun deletePostLike(
        postId: Long,
        contestType: String,
    ): PostLikeDto? = safeAPICall {
        postLikeService.deletePostLike(
            request = PostLikeRequest(postId = postId, contestType = contestType)
        )
    }.body?.responseData?.toPostLikeDto()
}