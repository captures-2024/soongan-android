package com.captures2024.soongan.core.model.network.response.comments

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCommentRepliesResponse(
    @SerialName("comments")
    val comments: List<GetCommentRepliesCommentInfoResponse>,
    @SerialName("pageInfo")
    val pageInfo: GetCommentRepliesPageInfoResponse,
)
