package com.captures2024.soongan.core.model.exception

import kotlinx.serialization.Serializable

@Serializable
data class ExceptionResponse(
    val statusCode: Int,
    val message: String?,
    val detailMessage: String?,
)