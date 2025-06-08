package com.captures2024.soongan.data.source.contest.impl.remote

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.model.dto.PostLikeDto
import com.captures2024.soongan.core.model.network.request.like.PostLikeRequest
import com.captures2024.soongan.data.source.contest.impl.mapper.toPostLikeDto
import com.captures2024.soongan.data.source.contest.remote.PostLikeRemoteDataSource
import com.captures2024.soongan.data.source.contest.impl.service.PostLikeService
import com.captures2024.soongan.data.source.utils.safeAPICall
import javax.inject.Inject

class PostLikeRemoteDataSourceImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val postLikeService: PostLikeService,
) : PostLikeRemoteDataSource {

    init {
        analyticsHelper.d { "PostLikeRemoteDataSource::init" }
    }

    override suspend fun putPostLike(
        postId: Long,
        contestType: String,
    ): PostLikeDto? {
        analyticsHelper.d { "putPostLike - postId: $postId, contestType: $contestType" }

        val response = safeAPICall {
            postLikeService.putPostLike(
                request = PostLikeRequest(
                    postId = postId,
                    contestType = contestType,
                ),
            )
        }

        val responseHeader = response.headers

        analyticsHelper.d { "putPostLike - responseHeader: $responseHeader" }

        val responseBody = response.body

        analyticsHelper.d { "putPostLike - responseBody: $responseBody" }

        return responseBody?.responseData?.toPostLikeDto()
    }

    override suspend fun deletePostLike(
        postId: Long,
        contestType: String,
    ): PostLikeDto? {
        analyticsHelper.d { "deletePostLike - postId: $postId, contestType: $contestType" }

        val response = safeAPICall {
            postLikeService.deletePostLike(
                request = PostLikeRequest(
                    postId = postId,
                    contestType = contestType,
                ),
            )
        }

        val responseHeader = response.headers

        analyticsHelper.d { "deletePostLike - responseHeader: $responseHeader" }

        val responseBody = response.body

        analyticsHelper.d { "deletePostLike - responseBody: $responseBody" }

        return responseBody?.responseData?.toPostLikeDto()
    }
}
