package com.captures2024.soongan.core.android.helper

import com.captures2024.soongan.core.android.utils.LogElementArgument
import com.captures2024.soongan.core.android.utils.LogLevel

interface AnalyticsHelper {
    fun initialize()

    fun v(
        vararg logVariable: LogElementArgument,
        message: String? = null,
        tag: String? = null,
    )

    fun d(
        vararg logVariable: LogElementArgument,
        message: String? = null,
        tag: String? = null,
    )

    fun i(
        vararg logVariable: LogElementArgument,
        message: String? = null,
        tag: String? = null,
    )

    fun w(
        vararg logVariable: LogElementArgument,
        message: String? = null,
        tag: String? = null,
    )

    fun e(
        throwable: Throwable? = null,
        vararg logVariable: LogElementArgument,
        message: String? = null,
        tag: String? = null,
    )

    fun logIf(
        condition: () -> Boolean,
        level: LogLevel,
        vararg logVariable: LogElementArgument,
        throwable: Throwable? = null,
        message: String? = null,
        tag: String? = null,
    )
}
