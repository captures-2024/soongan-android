package com.captures2024.soongan.core.model.exception

sealed interface UIException {
    data class SignException(
        override val cause: Throwable? = null,
    ) : Exception("SignException", cause), UIException

    data class UnknownException(
        override val message: String? = null,
        override val cause: Throwable? = null,
    ) : Exception(message, cause), UIException
}
