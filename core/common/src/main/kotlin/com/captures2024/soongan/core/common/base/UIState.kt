package com.captures2024.soongan.core.common.base

import com.captures2024.soongan.core.analytics.utils.LogElementArgument

interface UIState {

    fun toLoggingElements(): Array<LogElementArgument>
}
