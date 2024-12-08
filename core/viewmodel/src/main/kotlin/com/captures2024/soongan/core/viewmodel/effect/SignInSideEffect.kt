package com.captures2024.soongan.core.viewmodel.effect

import com.captures2024.soongan.core.common.base.UISideEffect

sealed interface SignInSideEffect : UISideEffect {

    data object GoogleSignIn : SignInSideEffect

    data object KakaoSignIn : SignInSideEffect

    data object SuccessSocialSign : SignInSideEffect

    data object NavigateToTermsOfUse : SignInSideEffect

    data object NavigateToPrivacyPolicy : SignInSideEffect

    data class NavigateToSignUp(
        val nickname: String?,
    ) : SignInSideEffect

    data object NavigateToMain : SignInSideEffect
}