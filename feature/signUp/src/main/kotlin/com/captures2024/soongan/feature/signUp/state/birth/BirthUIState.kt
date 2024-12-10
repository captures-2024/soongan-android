package com.captures2024.soongan.feature.signUp.state.birth

import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.Validation
import com.captures2024.soongan.core.common.base.UIState

internal data class BirthUIState(
    val nickname: String,
    val isLoading: Boolean = false,
    val birthYear: String = "",
) : UIState {
    val isValid
        get() = Validation.isValidBirthYear(birthYear)

    override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
        LogElementArgument("nickname", nickname),
        LogElementArgument("isLoading", isLoading.toString()),
        LogElementArgument("birthDate", birthYear),
    )
}