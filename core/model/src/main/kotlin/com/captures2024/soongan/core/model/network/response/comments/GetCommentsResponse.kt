package com.captures2024.soongan.core.model.network.response.comments

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCommentsResponse(
    @SerialName("postId")
    val postId: Int,
    @SerialName("comments")
    val comments: List<GetCommentsCommentInfoResponse>,
    @SerialName("pageInfo")
    val pageInfo: GetCommentsPageInfoResponse,
)
