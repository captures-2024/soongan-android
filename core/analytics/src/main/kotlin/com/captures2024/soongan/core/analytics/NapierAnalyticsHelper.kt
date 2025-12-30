package com.captures2024.soongan.core.analytics

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.analytics.helper.LoggingHelper
import com.captures2024.soongan.core.analytics.utils.LogLevel
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import java.util.regex.Pattern

class NapierAnalyticsHelper : AnalyticsHelper() {
    override fun initLogger() {
        Napier.base(DebugAntilog())
    }

    override fun setTag(): Pair<String, String> {
        val ignoreClassList = listOf(
            AnalyticsHelper::class.java.name,
            LoggingHelper::class.java.name,
            NapierAnalyticsHelper::class.java.name,
        )

        val pair = Throwable("current").stackTrace
            .first { it.className.split("$")[0] !in ignoreClassList }
            .let(this::createStacktraceElementTag)

        return pair
    }

    override fun createStacktraceElementTag(element: StackTraceElement): Pair<String, String> {
        var tag = element.className.substringAfterLast('.')

        val m = Pattern.compile("(\\$\\d+)+$").matcher(tag)

        if (m.find()) {
            tag = m.replaceAll("")
        }

        val fileName = element.fileName ?: "null"
        val lineNumber = element.lineNumber.toString()
        val methodName = element.methodName

        val defaultFormatingLog = "fileName: $fileName, lineNumber: $lineNumber, methodName: $methodName, "

        return if (tag.length <= 23) {
            tag to defaultFormatingLog
        } else {
            tag.substring(0, 23) to defaultFormatingLog
        }
    }

    override fun v(
        tag: String?,
        formatingMessage: Boolean,
        message: () -> String,
    ) {
        val (defaultTag, defaultFormatingLog) = setTag()
        val inputLog: String = message()

        val logMessage = when (formatingMessage) {
            true -> defaultFormatingLog + inputLog
            false -> inputLog
        }

        val currentTag = tag ?: defaultTag

        Napier.v(
            tag = currentTag,
            message = logMessage,
        )
    }

    override fun d(
        tag: String?,
        formatingMessage: Boolean,
        message: () -> String,
    ) {
        val (defaultTag, defaultFormatingLog) = setTag()
        val inputLog: String = message()

        val logMessage = when (formatingMessage) {
            true -> defaultFormatingLog + inputLog
            false -> inputLog
        }

        val currentTag = tag ?: defaultTag

        Napier.d(
            tag = currentTag,
            message = logMessage,
        )
    }

    override fun i(
        tag: String?,
        formatingMessage: Boolean,
        message: () -> String,
    ) {
        val (defaultTag, defaultFormatingLog) = setTag()
        val inputLog: String = message()

        val logMessage = when (formatingMessage) {
            true -> defaultFormatingLog + inputLog
            false -> inputLog
        }

        val currentTag = tag ?: defaultTag

        Napier.i(
            tag = currentTag,
            message = logMessage,
        )
    }

    override fun w(
        tag: String?,
        formatingMessage: Boolean,
        message: () -> String,
    ) {
        val (defaultTag, defaultFormatingLog) = setTag()
        val inputLog: String = message()

        val logMessage = when (formatingMessage) {
            true -> defaultFormatingLog + inputLog
            false -> inputLog
        }

        val currentTag = tag ?: defaultTag

        Napier.w(
            tag = currentTag,
            message = logMessage,
        )
    }

    override fun e(
        throwable: Throwable?,
        tag: String?,
        formatingMessage: Boolean,
        message: () -> String,
    ) {
        val (defaultTag, defaultFormatingLog) = setTag()
        val inputLog: String = message()

        val logMessage = when (formatingMessage) {
            true -> defaultFormatingLog + inputLog
            false -> inputLog
        }

        val currentTag = tag ?: defaultTag

        Napier.e(
            throwable = throwable,
            tag = currentTag,
            message = logMessage,
        )
    }

    override fun logIf(
        condition: () -> Boolean,
        level: LogLevel,
        throwable: Throwable?,
        tag: String?,
        formatingMessage: Boolean,
        message: () -> String,
    ) {
        if (!condition()) {
            return
        }

        when (level) {
            LogLevel.VERBOSE -> v(
                tag = tag,
                formatingMessage = formatingMessage,
                message = message,
            )

            LogLevel.DEBUG -> d(
                tag = tag,
                formatingMessage = formatingMessage,
                message = message,
            )

            LogLevel.INFO -> i(
                tag = tag,
                formatingMessage = formatingMessage,
                message = message,
            )

            LogLevel.WARN -> w(
                tag = tag,
                formatingMessage = formatingMessage,
                message = message,
            )

            LogLevel.ERROR -> e(
                throwable = throwable,
                tag = tag,
                formatingMessage = formatingMessage,
                message = message,
            )
        }
    }
}
