package com.captures2024.soongan.core.model.network.response.awards

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAwardsDetailInfoResponse(
    @SerialName("postsCount")
    val postsCount: Long,
    @SerialName("firstPrizePost")
    val firstPrizePost: GetAwardsDetailInfoPostResponse,
    @SerialName("otherTop7Posts")
    val otherTop7Posts: List<GetAwardsDetailInfoPostResponse>,
)
