package com.captures2024.soongan.core.analytics

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.analytics.utils.LogLevel

class DefaultAnalyticsHelper : AnalyticsHelper() {
    override fun initLogger() = Unit

    override fun setTag(): Pair<String, List<LogElementArgument>> {
        TODO("Not yet implemented")
    }

    override fun createStacktraceElementTag(element: StackTraceElement): Pair<String, List<LogElementArgument>> {
        TODO("Not yet implemented")
    }

    override fun v(
        vararg logVariable: LogElementArgument,
        message: String?,
        tag: String?,
    ) = Unit

    override fun d(
        vararg logVariable: LogElementArgument,
        message: String?,
        tag: String?,
    ) = Unit

    override fun i(
        vararg logVariable: LogElementArgument,
        message: String?,
        tag: String?,
    ) = Unit

    override fun w(
        vararg logVariable: LogElementArgument,
        message: String?,
        tag: String?,
    ) = Unit

    override fun e(
        throwable: Throwable?,
        vararg logVariable: LogElementArgument,
        message: String?,
        tag: String?,
    ) = Unit

    override fun logIf(
        condition: () -> Boolean,
        level: LogLevel,
        vararg logVariable: LogElementArgument,
        throwable: Throwable?,
        message: String?,
        tag: String?,
    ) = Unit

    override fun networkLog(message: String?) = Unit
}
