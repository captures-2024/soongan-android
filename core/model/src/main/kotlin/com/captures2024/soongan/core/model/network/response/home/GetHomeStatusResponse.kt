package com.captures2024.soongan.core.model.network.response.home

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetHomeStatusResponse(
    @SerialName("contestInfo")
    val contestInfo: GetHomeStatusContestInfoResponse,
    @SerialName("postInfo")
    val postInfo: List<GetHomeStatusPostInfoResponse>,
)
