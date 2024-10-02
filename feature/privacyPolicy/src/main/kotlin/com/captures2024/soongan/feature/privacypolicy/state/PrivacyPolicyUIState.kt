package com.captures2024.soongan.feature.privacypolicy.state

import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.UIState

internal data class PrivacyPolicyUIState(
    val isLoading: Boolean = false
) : UIState {

    override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
        LogElementArgument("isLoading", isLoading.toString())
    )
}