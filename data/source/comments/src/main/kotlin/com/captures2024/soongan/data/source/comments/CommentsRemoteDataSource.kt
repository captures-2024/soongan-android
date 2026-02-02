package com.captures2024.soongan.data.source.comments

import com.captures2024.soongan.core.model.network.ContestType
import com.captures2024.soongan.core.model.network.response.comments.GetCommentRepliesResponse
import com.captures2024.soongan.core.model.network.response.comments.GetCommentsResponse

interface CommentsRemoteDataSource {

    suspend fun getComments(
        contestType: ContestType,
        postId: Int,
    ): GetCommentsResponse?

    suspend fun getComments(
        contestType: ContestType,
        postId: Int,
        page: Int,
    ): GetCommentsResponse?

    suspend fun getComments(
        contestType: ContestType,
        postId: Int,
        page: Int,
        size: Int,
    ): GetCommentsResponse?

    suspend fun modifyComment(
        contestType: ContestType,
        postId: Int,
        commentId: Int,
        commentText: String,
    )

    suspend fun postComment(
        contestType: ContestType,
        postId: Int,
        commentText: String,
        parentCommentId: Int,
    )

    // TODO 수정 필요
    suspend fun deleteComment()

    suspend fun getCommentReplies(
        contestType: ContestType,
        parentCommentId: Int,
    ): GetCommentRepliesResponse?

    suspend fun getCommentReplies(
        contestType: ContestType,
        parentCommentId: Int,
        page: Int,
    ): GetCommentRepliesResponse?

    suspend fun getCommentReplies(
        contestType: ContestType,
        parentCommentId: Int,
        page: Int,
        size: Int,
    ): GetCommentRepliesResponse?
}
