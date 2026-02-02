package com.captures2024.soongan.core.model.network.request.comments

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostCommentRequest(
    @SerialName("contestType")
    val contestType: String,
    @SerialName("postId")
    val postId: Int,
    @SerialName("commentText")
    val commentText: String,
    @SerialName("parentCommentId")
    val parentCommentId: Int,
)
