package com.captures2024.soongan.state

import com.captures2024.soongan.core.common.base.UIIntent

internal sealed interface AppRootIntent : UIIntent {

    data class FetchFCMToken(
        val token: String,
    ) : AppRootIntent

    data object SuccessSign : AppRootIntent

    data object NavigateToMain : AppRootIntent
}