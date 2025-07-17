package com.captures2024.soongan.core.model.network.response.awards

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetOtherTop7PostResponse(
    @SerialName("postId")
    val postId: Long,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("ranking")
    val ranking: Long,
    @SerialName("score")
    val score: Long,
    @SerialName("status")
    val status: String? = null,
)
