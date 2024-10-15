package com.captures2024.soongan.core.analytics

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.analytics.helper.LoggingHelper
import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.analytics.utils.LogLevel
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import java.util.regex.Pattern

class NapierAnalyticsHelper : AnalyticsHelper() {
    override fun initLogger() {
        Napier.base(DebugAntilog())
    }

    override fun setTag(): Pair<String, List<LogElementArgument>> {
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

    override fun createStacktraceElementTag(element: StackTraceElement): Pair<String, List<LogElementArgument>> {
        var tag = element.className.substringAfterLast('.')

        val m = Pattern.compile("(\\$\\d+)+$").matcher(tag)

        if (m.find()) {
            tag = m.replaceAll("")
        }

        val list = mutableListOf(
            LogElementArgument(
                "fileName",
                element.fileName ?: "null"
            ),
            LogElementArgument(
                "lineNumber",
                element.lineNumber.toString()
            ),
            LogElementArgument(
                "methodName",
                element.methodName
            ),
        )

        return if (tag.length <= 23) {
            tag to list
        } else {
            tag.substring(0, 23) to list
        }
    }

    override fun v(
        vararg logVariable: LogElementArgument,
        message: String?,
        tag: String?,
    ) {
        val (defaultTag, defaultArg) = setTag()

        val currentTag = tag ?: defaultTag

        val sb = StringBuilder().apply {
            append("$message\n")
            defaultArg.forEach {
                append("${it.key} : ${it.value}\n")
            }
            logVariable.forEach { append("${it.key} : ${it.value}\n") }
        }

        Napier.v(
            message = sb.toString(),
            tag = currentTag,
        )
    }

    override fun d(
        vararg logVariable: LogElementArgument,
        message: String?,
        tag: String?,
    ) {
        val (defaultTag, defaultArg) = setTag()

        val currentTag = tag ?: defaultTag

        val sb = StringBuilder().apply {
            append("$message\n")
            defaultArg.forEach { append("${it.key} : ${it.value}\n") }
            logVariable.forEach { append("${it.key} : ${it.value}\n") }
        }

        Napier.d(
            message = sb.toString(),
            tag = currentTag,
        )
    }

    override fun i(
        vararg logVariable: LogElementArgument,
        message: String?,
        tag: String?,
    ) {
        val (defaultTag, defaultArg) = setTag()

        val currentTag = tag ?: defaultTag

        val sb = StringBuilder().apply {
            append("$message\n")
            defaultArg.forEach { append("${it.key} : ${it.value}\n") }
            logVariable.forEach { append("${it.key} : ${it.value}\n") }
        }

        Napier.i(
            message = sb.toString(),
            tag = currentTag,
        )
    }

    override fun w(
        vararg logVariable: LogElementArgument,
        message: String?,
        tag: String?,
    ) {
        val (defaultTag, defaultArg) = setTag()

        val currentTag = tag ?: defaultTag

        val sb = StringBuilder().apply {
            append("$message\n")
            defaultArg.forEach { append("${it.key} : ${it.value}\n") }
            logVariable.forEach { append("${it.key} : ${it.value}\n") }
        }

        Napier.w(
            message = sb.toString(),
            tag = currentTag,
        )
    }

    override fun e(
        throwable: Throwable?,
        vararg logVariable: LogElementArgument,
        message: String?,
        tag: String?,
    ) {
        val (defaultTag, defaultArg) = setTag()

        val currentTag = tag ?: defaultTag

        val sb = StringBuilder().apply {
            append("$message\n")
            defaultArg.forEach { append("${it.key} : ${it.value}\n") }
            logVariable.forEach { append("${it.key} : ${it.value}\n") }
        }

        Napier.e(
            throwable = throwable,
            message = sb.toString(),
            tag = currentTag,
        )
    }

    override fun logIf(
        condition: () -> Boolean,
        level: LogLevel,
        vararg logVariable: LogElementArgument,
        throwable: Throwable?,
        message: String?,
        tag: String?,
    ) {
        if (!condition()) {
            return
        }

        when (level) {
            LogLevel.VERBOSE -> v(
                logVariable = logVariable,
                message = message,
                tag = tag,
            )

            LogLevel.DEBUG -> d(
                logVariable = logVariable,
                message = message,
                tag = tag,
            )

            LogLevel.INFO -> i(
                logVariable = logVariable,
                message = message,
                tag = tag,
            )

            LogLevel.WARN -> w(
                logVariable = logVariable,
                message = message,
                tag = tag,
            )

            LogLevel.ERROR -> e(
                throwable = throwable,
                logVariable = logVariable,
                message = message,
                tag = tag,
            )
        }
    }

    override fun networkLog(message: String?) {
        val (defaultTag, _) = setTag()

        Napier.d(
            message = "$message\n",
            tag = defaultTag,
        )
    }
}