package com.captures2024.soongan.data.source.comments.impl

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.model.network.ContestType
import com.captures2024.soongan.core.model.network.request.comments.ModifyCommentRequest
import com.captures2024.soongan.core.model.network.request.comments.PostCommentRequest
import com.captures2024.soongan.core.model.network.response.BaseResponse
import com.captures2024.soongan.core.model.network.response.comments.GetCommentRepliesResponse
import com.captures2024.soongan.core.model.network.response.comments.GetCommentsResponse
import com.captures2024.soongan.data.service.api.CommentsAPI
import com.captures2024.soongan.data.service.api.utils.BaseAPIResult
import com.captures2024.soongan.data.service.api.utils.safeAPICall
import com.captures2024.soongan.data.source.comments.CommentsRemoteDataSource
import javax.inject.Inject

class CommentsRemoteDataSourceImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val commentsAPI: CommentsAPI,
) : CommentsRemoteDataSource {
    init {
        analyticsHelper.d { "CommentsRemoteDataSource::init" }
    }

    override suspend fun getComments(
        contestType: ContestType,
        postId: Int,
    ): GetCommentsResponse? {
        analyticsHelper.d { "getComments - contestType: $contestType" }
        analyticsHelper.d { "getComments - postId: $postId" }

        val response: BaseAPIResult<BaseResponse<GetCommentsResponse>> = safeAPICall {
            commentsAPI.getComments(
                contestType = contestType.name,
                postId = postId,
            )
        }

        val responseHeader: Map<String, List<String>> = response.headers

        analyticsHelper.d { "getComments - responseHeader: $responseHeader" }

        val responseBody: BaseResponse<GetCommentsResponse>? = response.body

        analyticsHelper.d { "getComments - responseBody: $responseBody" }

        return responseBody?.responseData
    }

    override suspend fun getComments(
        contestType: ContestType,
        postId: Int,
        page: Int,
    ): GetCommentsResponse? {
        analyticsHelper.d { "getComments - contestType: $contestType" }
        analyticsHelper.d { "getComments - postId: $postId" }
        analyticsHelper.d { "getComments - page: $page" }

        val response: BaseAPIResult<BaseResponse<GetCommentsResponse>> = safeAPICall {
            commentsAPI.getComments(
                contestType = contestType.name,
                postId = postId,
                page = page,
            )
        }

        val responseHeader: Map<String, List<String>> = response.headers

        analyticsHelper.d { "getComments - responseHeader: $responseHeader" }

        val responseBody: BaseResponse<GetCommentsResponse>? = response.body

        analyticsHelper.d { "getComments - responseBody: $responseBody" }

        return responseBody?.responseData
    }

    override suspend fun getComments(
        contestType: ContestType,
        postId: Int,
        page: Int,
        size: Int,
    ): GetCommentsResponse? {
        analyticsHelper.d { "getComments - contestType: $contestType" }
        analyticsHelper.d { "getComments - postId: $postId" }
        analyticsHelper.d { "getComments - page: $page" }
        analyticsHelper.d { "getComments - size: $size" }

        val response: BaseAPIResult<BaseResponse<GetCommentsResponse>> = safeAPICall {
            commentsAPI.getComments(
                contestType = contestType.name,
                postId = postId,
                page = page,
                size = size,
            )
        }

        val responseHeader: Map<String, List<String>> = response.headers

        analyticsHelper.d { "getComments - responseHeader: $responseHeader" }

        val responseBody: BaseResponse<GetCommentsResponse>? = response.body

        analyticsHelper.d { "getComments - responseBody: $responseBody" }

        return responseBody?.responseData
    }

    override suspend fun modifyComment(
        contestType: ContestType,
        postId: Int,
        commentId: Int,
        commentText: String,
    ) {
        analyticsHelper.d { "modifyComment - contestType: $contestType" }
        analyticsHelper.d { "modifyComment - postId: $postId" }
        analyticsHelper.d { "modifyComment - commentId: $commentId" }
        analyticsHelper.d { "modifyComment - commentText: $commentText" }

        val response: BaseAPIResult<BaseResponse<Unit>> = safeAPICall {
            commentsAPI.modifyComment(
                request = ModifyCommentRequest(
                    contestType = contestType.name,
                    postId = postId,
                    commentId = commentId,
                    commentText = commentText,
                ),
            )
        }

        val responseHeader: Map<String, List<String>> = response.headers

        analyticsHelper.d { "modifyComment - responseHeader: $responseHeader" }

        val responseBody: BaseResponse<Unit>? = response.body

        analyticsHelper.d { "modifyComment - responseBody: $responseBody" }
    }

    override suspend fun postComment(
        contestType: ContestType,
        postId: Int,
        commentText: String,
        parentCommentId: Int,
    ) {
        analyticsHelper.d { "postComment - contestType: $contestType" }
        analyticsHelper.d { "postComment - postId: $postId" }
        analyticsHelper.d { "postComment - commentText: $commentText" }
        analyticsHelper.d { "postComment - parentCommentId: $parentCommentId" }

        val response: BaseAPIResult<BaseResponse<Unit>> = safeAPICall {
            commentsAPI.postComment(
                request = PostCommentRequest(
                    contestType = contestType.name,
                    postId = postId,
                    commentText = commentText,
                    parentCommentId = parentCommentId,
                ),
            )
        }

        val responseHeader: Map<String, List<String>> = response.headers

        analyticsHelper.d { "postComment - responseHeader: $responseHeader" }

        val responseBody: BaseResponse<Unit>? = response.body

        analyticsHelper.d { "postComment - responseBody: $responseBody" }
    }

    override suspend fun deleteComment() {
        TODO("Not yet implemented")
    }

    override suspend fun getCommentReplies(
        contestType: ContestType,
        parentCommentId: Int,
    ): GetCommentRepliesResponse? {
        analyticsHelper.d { "getCommentReplies - contestType: $contestType" }
        analyticsHelper.d { "getCommentReplies - parentCommentId: $parentCommentId" }

        val response: BaseAPIResult<BaseResponse<GetCommentRepliesResponse>> = safeAPICall {
            commentsAPI.getCommentReplies(
                contestType = contestType.name,
                parentCommentId = parentCommentId,
            )
        }

        val responseHeader: Map<String, List<String>> = response.headers

        analyticsHelper.d { "getCommentReplies - responseHeader: $responseHeader" }

        val responseBody: BaseResponse<GetCommentRepliesResponse>? = response.body

        analyticsHelper.d { "getCommentReplies - responseBody: $responseBody" }

        return responseBody?.responseData
    }

    override suspend fun getCommentReplies(
        contestType: ContestType,
        parentCommentId: Int,
        page: Int,
    ): GetCommentRepliesResponse? {
        analyticsHelper.d { "getCommentReplies - contestType: $contestType" }
        analyticsHelper.d { "getCommentReplies - parentCommentId: $parentCommentId" }
        analyticsHelper.d { "getCommentReplies - page: $page" }

        val response: BaseAPIResult<BaseResponse<GetCommentRepliesResponse>> = safeAPICall {
            commentsAPI.getCommentReplies(
                contestType = contestType.name,
                parentCommentId = parentCommentId,
                page = page,
            )
        }

        val responseHeader: Map<String, List<String>> = response.headers

        analyticsHelper.d { "getCommentReplies - responseHeader: $responseHeader" }

        val responseBody: BaseResponse<GetCommentRepliesResponse>? = response.body

        analyticsHelper.d { "getCommentReplies - responseBody: $responseBody" }

        return responseBody?.responseData
    }

    override suspend fun getCommentReplies(
        contestType: ContestType,
        parentCommentId: Int,
        page: Int,
        size: Int,
    ): GetCommentRepliesResponse? {
        analyticsHelper.d { "getCommentReplies - contestType: $contestType" }
        analyticsHelper.d { "getCommentReplies - parentCommentId: $parentCommentId" }
        analyticsHelper.d { "getCommentReplies - page: $page" }
        analyticsHelper.d { "getCommentReplies - size: $size" }

        val response: BaseAPIResult<BaseResponse<GetCommentRepliesResponse>> = safeAPICall {
            commentsAPI.getCommentReplies(
                contestType = contestType.name,
                parentCommentId = parentCommentId,
                page = page,
                size = size,
            )
        }

        val responseHeader: Map<String, List<String>> = response.headers

        analyticsHelper.d { "getCommentReplies - responseHeader: $responseHeader" }

        val responseBody: BaseResponse<GetCommentRepliesResponse>? = response.body

        analyticsHelper.d { "getCommentReplies - responseBody: $responseBody" }

        return responseBody?.responseData
    }
}
