package com.captures2024.soongan.feature.signUp.state.birthdate

import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.UIState

internal data class BirthDateUIState(
    val nickname: String,
    val isLoading: Boolean = false,
    val birthDate: String = "",
) : UIState {

    override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
        LogElementArgument("nickname", nickname),
        LogElementArgument("isLoading", isLoading.toString()),
        LogElementArgument("birthDate", birthDate),
    )
}