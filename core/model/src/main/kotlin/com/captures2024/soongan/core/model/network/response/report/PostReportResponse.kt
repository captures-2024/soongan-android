package com.captures2024.soongan.core.model.network.response.report

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostReportResponse(
    @SerialName("id")
    val id: Long,
    @SerialName("reportMemberId")
    val reportMemberId: Long,
    @SerialName("targetMemberId")
    val targetMemberId: Long,
    @SerialName("targetId")
    val targetId: Long,
    @SerialName("targetType")
    val targetType: String,
    @SerialName("reportType")
    val reportType: String,
    @SerialName("reason")
    val reason: String? = null,
    @SerialName("reportHistories")
    val reportHistories: List<ReportHistoryResponse>,
)
