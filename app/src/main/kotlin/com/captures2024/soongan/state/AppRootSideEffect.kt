package com.captures2024.soongan.state

import com.captures2024.soongan.core.common.base.UISideEffect

internal sealed interface AppRootSideEffect : UISideEffect {

    data class FetchFcmToken(
        val token: String,
    ) : AppRootSideEffect

    data object FailedRemoteSyncData : AppRootSideEffect

    data class SuccessRemoteSyncData(
        val nickname: String,
        val birthDate: String,
    ) : AppRootSideEffect
}