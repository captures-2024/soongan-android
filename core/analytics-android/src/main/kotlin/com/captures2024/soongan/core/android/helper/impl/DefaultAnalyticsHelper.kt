package com.captures2024.soongan.core.android.helper.impl

import com.captures2024.soongan.core.android.utils.LogElementArgument
import com.captures2024.soongan.core.android.utils.LogLevel
import com.captures2024.soongan.core.android.helper.AnalyticsHelper

class DefaultAnalyticsHelper : AnalyticsHelper {
    override fun initialize() {
        TODO("Not yet implemented")
    }

    override fun v(vararg logVariable: LogElementArgument, message: String?, tag: String?) {
        TODO("Not yet implemented")
    }

    override fun d(vararg logVariable: LogElementArgument, message: String?, tag: String?) {
        TODO("Not yet implemented")
    }

    override fun i(vararg logVariable: LogElementArgument, message: String?, tag: String?) {
        TODO("Not yet implemented")
    }

    override fun w(vararg logVariable: LogElementArgument, message: String?, tag: String?) {
        TODO("Not yet implemented")
    }

    override fun e(throwable: Throwable?, vararg logVariable: LogElementArgument, message: String?, tag: String?) {
        TODO("Not yet implemented")
    }

    override fun logIf(
        condition: () -> Boolean,
        level: LogLevel,
        vararg logVariable: LogElementArgument,
        throwable: Throwable?,
        message: String?,
        tag: String?,
    ) {
        TODO("Not yet implemented")
    }
}
