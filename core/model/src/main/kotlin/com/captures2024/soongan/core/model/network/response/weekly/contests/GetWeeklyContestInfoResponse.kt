package com.captures2024.soongan.core.model.network.response.weekly.contests

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetWeeklyContestInfoResponse(
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
    @SerialName("voteStartAt")
    val voteStartAt: String,
    @SerialName("voteEndAt")
    val voteEndAt: String,
    @SerialName("announcedAt")
    val announcedAt: String,
)
