package com.captures2024.soongan.core.viewmodel.effect

import com.captures2024.soongan.core.common.base.UISideEffect

sealed interface SignSideEffect : UISideEffect {

    data object GoogleSignIn : SignSideEffect

    data object KakaoSignIn : SignSideEffect

    data object SuccessSocialSign : SignSideEffect

    data object NavigateToTermsOfUse : SignSideEffect

    data object NavigateToPrivacyPolicy : SignSideEffect

    data class NavigateToSignUp(
        val nickname: String?,
    ) : SignSideEffect

    data object NavigateToMain : SignSideEffect

    data class PatchInfo(
        val nickname: String,
        val birthYear: Int,
    ) : SignSideEffect
}