package com.captures2024.soongan.core.model.network.response.home

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetHomeStatusContestInfoResponse(
    @SerialName("contestType")
    val contestType: String,
    @SerialName("subject")
    val subject: String,
    @SerialName("startAt")
    val startAt: String,
    @SerialName("endAt")
    val endAt: String,
)
