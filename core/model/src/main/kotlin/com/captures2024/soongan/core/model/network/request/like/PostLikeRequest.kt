package com.captures2024.soongan.core.model.network.request.like

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostLikeRequest(
    @SerialName("postId")
    val postId: Long,
    @SerialName("contestType")
    val contestType: String,
)
