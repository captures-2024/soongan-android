package com.captures2024.soongan.core.model.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    @SerialName("statusCode")
    val statusCode: String,
    @SerialName("message")
    val message: String,
    @SerialName("responseData")
    val responseData: T?,
)