package com.captures2024.soongan.core.model.network.response.weekly.contests

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetGalleryResponse(
    @SerialName("round")
    val round: Int?,
    @SerialName("subject")
    val subject: String,
    @SerialName("posts")
    val posts: List<GetGalleryPostInfoResponse>,
    @SerialName("pageInfo")
    val pageInfo: GetGalleryPageInfoResponse,
)
