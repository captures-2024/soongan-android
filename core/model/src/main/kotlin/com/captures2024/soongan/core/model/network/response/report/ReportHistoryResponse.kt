package com.captures2024.soongan.core.model.network.response.report

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReportHistoryResponse(
    @SerialName("id")
    val id: Long,
    @SerialName("targetId")
    val targetId: Long,
    @SerialName("targetType")
    val targetType: String,
)
