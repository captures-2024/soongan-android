package com.captures2024.soongan.feature.intro.state

import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.UIState

data class IntroUIState(
    val useDarkTheme: Boolean = false,
    val disableDynamicTheming: Boolean = false,
    val useAndroidTheme: Boolean = false
) : UIState {

    override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
        LogElementArgument("useDarkTheme", useDarkTheme.toString()),
        LogElementArgument("disableDynamicTheming", disableDynamicTheming.toString()),
        LogElementArgument("useAndroidTheme", useAndroidTheme.toString()),
    )
}