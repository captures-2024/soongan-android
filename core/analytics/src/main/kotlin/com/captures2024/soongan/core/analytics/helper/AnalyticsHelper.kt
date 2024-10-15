package com.captures2024.soongan.core.analytics.helper

import com.captures2024.soongan.core.analytics.utils.LogElementArgument

abstract class AnalyticsHelper : LoggingHelper {
    fun initialize() {
        initLogger()
    }

    protected abstract fun initLogger()

    protected abstract fun setTag(): Pair<String, List<LogElementArgument>>
    protected abstract fun createStacktraceElementTag(element: StackTraceElement): Pair<String, List<LogElementArgument>>
}
