package com.captures2024.soongan.core.model.network.response.like

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostLikeResponse(
    @SerialName("postId")
    val postId: Long,
    @SerialName("likeCount")
    val likeCount: Int,
)
