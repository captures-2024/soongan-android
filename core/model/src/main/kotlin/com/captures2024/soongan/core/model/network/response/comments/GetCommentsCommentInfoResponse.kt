package com.captures2024.soongan.core.model.network.response.comments

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCommentsCommentInfoResponse(
    @SerialName("commentId")
    val commentId: Int,
    @SerialName("memberId")
    val memberId: Int,
    @SerialName("memberNickname")
    val memberNickname: String,
    @SerialName("commentText")
    val commentText: String,
    @SerialName("parentCommentID")
    val parentCommentID: Int,
    @SerialName("commentStatus")
    val commentStatus: String,
)
