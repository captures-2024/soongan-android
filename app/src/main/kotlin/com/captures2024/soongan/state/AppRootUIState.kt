package com.captures2024.soongan.state

import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.UIState

internal data class AppRootUIState(
    val isLoading: Boolean = false,
    val rootRouteState: AppRootRouteState = AppRootRouteState.LANDING,
    private val fcmToken: String = "",
    private val accessToken: String = "",
    private val refreshToken: String = "",
) : UIState {

    override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
        LogElementArgument("isLoading", isLoading.toString()),
    )
}