package com.captures2024.soongan.core.viewmodel.intent

import com.captures2024.soongan.core.common.base.UIIntent

sealed interface AppRootIntent : UIIntent {

    data object FetchFCMToken : AppRootIntent

    data object SuccessSign : AppRootIntent

    data object NavigateToMain : AppRootIntent

    data class PatchMemberInfo(
        val nickname: String,
        val birthYear: Int,
    ) : AppRootIntent
}