package com.captures2024.soongan.core.analytics.helper

abstract class AnalyticsHelper : LoggingHelper {
    fun initialize() {
        initLogger()
    }

    protected abstract fun initLogger()

    protected abstract fun setTag(): Pair<String, String>
    protected abstract fun createStacktraceElementTag(element: StackTraceElement): Pair<String, String>
}
