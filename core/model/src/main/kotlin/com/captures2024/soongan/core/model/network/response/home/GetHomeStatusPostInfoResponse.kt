package com.captures2024.soongan.core.model.network.response.home

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetHomeStatusPostInfoResponse(
    @SerialName("postId")
    val postId: Long,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("likeCount")
    val likeCount: Int,
    @SerialName("commentCount")
    val commentCount: Int,
)
