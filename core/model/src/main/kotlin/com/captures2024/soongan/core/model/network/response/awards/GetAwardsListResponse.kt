package com.captures2024.soongan.core.model.network.response.awards

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAwardsListResponse(
    @SerialName("contests")
    val contests: List<GetAwardsListBodyResponse>,
)
