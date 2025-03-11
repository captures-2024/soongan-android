package com.captures2024.soongan.core.model.network.request.weekly.contests

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EditPostRequest(
    @SerialName("title")
    val title: String,
)