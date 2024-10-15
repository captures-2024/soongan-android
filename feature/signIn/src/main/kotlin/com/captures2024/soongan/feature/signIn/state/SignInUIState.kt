package com.captures2024.soongan.feature.signIn.state

import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.UIState

data class SignInUIState(
    val isLoading: Boolean = false,
    val fcmToken: String = ""
) : UIState {

    override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
        LogElementArgument("isLoading", isLoading.toString()),
        LogElementArgument("fcmToken", fcmToken),
    )
}