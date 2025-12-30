package com.captures2024.soongan.core.model.network.response.weekly.contests

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterPostResponse(
    @SerialName("postId")
    val postId: Long,
    @SerialName("title")
    val title: String,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("registerNickname")
    val registerNickname: String,
)
