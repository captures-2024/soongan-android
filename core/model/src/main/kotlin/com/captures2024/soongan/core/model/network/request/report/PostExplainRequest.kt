package com.captures2024.soongan.core.model.network.request.report

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostExplainRequest(
    @SerialName("targetId")
    val targetId: Long,
    @SerialName("targetType")
    val targetType: String,
    @SerialName("explain")
    val explain: String,
)
