package com.captures2024.soongan.core.model.network.response.awards

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAwardsListBodyResponse(
    @SerialName("id")
    val id: Long,
    @SerialName("round")
    val round: Int,
    @SerialName("subject")
    val subject: String,
    @SerialName("startAt")
    val startAt: String,
    @SerialName("endAt")
    val endAt: String,
    @SerialName("announcedAt")
    val announcedAt: String,
    @SerialName("thumbnailImageUrl")
    val thumbnailImageUrl: String,
)
