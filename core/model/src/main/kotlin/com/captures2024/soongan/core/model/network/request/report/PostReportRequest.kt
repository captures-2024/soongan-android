package com.captures2024.soongan.core.model.network.request.report

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostReportRequest(
    @SerialName("targetId")
    val targetId: Long,
    @SerialName("targetType")
    val targetType: String,
    @SerialName("reportType")
    val reportType: String,
    @SerialName("reason")
    val reason: String? = null,

)