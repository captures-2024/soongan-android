package com.captures2024.soongan.core.analytics.helper

import com.captures2024.soongan.core.analytics.utils.LogLevel

interface LoggingHelper {

    fun v(
        tag: String? = null,
        formatingMessage: Boolean = false,
        message: () -> String,
    )

    fun d(
        tag: String? = null,
        formatingMessage: Boolean = false,
        message: () -> String,
    )

    fun i(
        tag: String? = null,
        formatingMessage: Boolean = false,
        message: () -> String,
    )

    fun w(
        tag: String? = null,
        formatingMessage: Boolean = false,
        message: () -> String,
    )

    fun e(
        throwable: Throwable? = null,
        tag: String? = null,
        formatingMessage: Boolean = false,
        message: () -> String,
    )

    fun logIf(
        condition: () -> Boolean,
        level: LogLevel,
        throwable: Throwable? = null,
        tag: String? = null,
        formatingMessage: Boolean = false,
        message: () -> String,
    )
}
