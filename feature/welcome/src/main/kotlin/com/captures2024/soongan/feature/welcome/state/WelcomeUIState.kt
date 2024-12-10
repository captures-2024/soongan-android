package com.captures2024.soongan.feature.welcome.state

import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.UIState

internal data class WelcomeUIState(
    val nickname: String,
) : UIState {
    override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
        LogElementArgument("nickname", nickname),
    )
}
