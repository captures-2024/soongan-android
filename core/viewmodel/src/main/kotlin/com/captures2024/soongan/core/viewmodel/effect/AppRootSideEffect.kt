package com.captures2024.soongan.core.viewmodel.effect

import com.captures2024.soongan.core.common.base.UISideEffect

sealed interface AppRootSideEffect : UISideEffect {

    data object FailedRemoteSyncData : AppRootSideEffect

    data class SuccessRemoteSyncData(
        val nickname: String?,
        val birthYear: Int?,
    ) : AppRootSideEffect
}