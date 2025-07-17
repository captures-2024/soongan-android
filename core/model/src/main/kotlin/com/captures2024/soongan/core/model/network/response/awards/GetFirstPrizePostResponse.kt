package com.captures2024.soongan.core.model.network.response.awards

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetFirstPrizePostResponse(
    @SerialName("postId")
    val postId: Long,
    @SerialName("title")
    val title: String,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("score")
    val score: Long,
    @SerialName("status")
    val status: String? = null,
)
