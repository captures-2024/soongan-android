package com.captures2024.soongan.core.model.exception

data class NetworkExceptionWrapper(
    val statusCode: Int? = null,
    override val message: String? = null,
    override val cause: Throwable? = null
) : Exception(message, cause)
