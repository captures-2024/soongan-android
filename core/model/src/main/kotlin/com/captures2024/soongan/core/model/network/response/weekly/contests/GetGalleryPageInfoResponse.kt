package com.captures2024.soongan.core.model.network.response.weekly.contests

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetGalleryPageInfoResponse(
    @SerialName("page")
    val page: Int,
    @SerialName("size")
    val size: Int,
    @SerialName("hasNext")
    val hasNext: Boolean,
)
