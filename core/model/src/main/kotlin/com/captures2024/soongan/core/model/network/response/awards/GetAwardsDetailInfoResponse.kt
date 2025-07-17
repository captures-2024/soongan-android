package com.captures2024.soongan.core.model.network.response.awards

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAwardsDetailInfoResponse(
    @SerialName("subject")
    val subject: String,
    @SerialName("round")
    val round: Long,
    @SerialName("startAt")
    val startAt: String,
    @SerialName("endAt")
    val endAt: String,
    @SerialName("postsCount")
    val postsCount: Long,
    @SerialName("firstPrizePost")
    val firstPrizePost: GetFirstPrizePostResponse,
    @SerialName("otherTop7Posts")
    val otherTop7Posts: List<GetOtherTop7PostResponse>,
)
