package com.captures2024.soongan.core.analytics

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.analytics.utils.LogLevel

class DefaultAnalyticsHelper : AnalyticsHelper() {
    override fun initLogger() = Unit

    override fun setTag(): Pair<String, String> {
        TODO("Not yet implemented")
    }

    override fun createStacktraceElementTag(element: StackTraceElement): Pair<String, String> {
        TODO("Not yet implemented")
    }

    override fun v(
        tag: String?,
        formatingMessage: Boolean,
        message: () -> String,
    ) = Unit

    override fun d(
        tag: String?,
        formatingMessage: Boolean,
        message: () -> String,
    ) = Unit

    override fun i(
        tag: String?,
        formatingMessage: Boolean,
        message: () -> String,
    ) = Unit

    override fun w(
        tag: String?,
        formatingMessage: Boolean,
        message: () -> String,
    ) = Unit

    override fun e(
        throwable: Throwable?,
        tag: String?,
        formatingMessage: Boolean,
        message: () -> String,
    ) = Unit

    override fun logIf(
        condition: () -> Boolean,
        level: LogLevel,
        throwable: Throwable?,
        tag: String?,
        formatingMessage: Boolean,
        message: () -> String,
    ) = Unit

    override fun networkLog(
        tag: String?,
        formatingMessage: Boolean,
        message: () -> String,
    ) = Unit
}
