package com.captures2024.soongan.core.viewmodel.state

import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.UIState

data class SignInUIState(
    val isLoading: Boolean = false,
    val fcmToken: String = "",
    val isNeedRegisterNickname: Boolean = false,
    val isNeedRegisterBirth: Boolean = false,
) : UIState {

    override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
        LogElementArgument("isLoading", isLoading.toString()),
        LogElementArgument("fcmToken", fcmToken),
        LogElementArgument("isNeedRegisterNickname", isNeedRegisterNickname.toString()),
        LogElementArgument("isNeedRegisterBirth", isNeedRegisterBirth.toString()),
    )
}