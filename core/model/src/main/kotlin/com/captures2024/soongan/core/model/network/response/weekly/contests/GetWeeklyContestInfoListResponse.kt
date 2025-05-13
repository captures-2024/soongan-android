package com.captures2024.soongan.core.model.network.response.weekly.contests

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetWeeklyContestInfoListResponse(
    @SerialName("contests")
    val weeklyContestInfoList: List<GetWeeklyContestInfoResponse>,
)
