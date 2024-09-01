package com.captures2024.soongan.feature.signIn.state

import com.captures2024.soongan.core.common.base.UISideEffect

sealed interface SignInSideEffect : UISideEffect {

    data object AppleSignIn : SignInSideEffect

    data object GoogleSignIn : SignInSideEffect

    data object KakaoSignIn : SignInSideEffect

    data object NavigateToMain : SignInSideEffect

    data object NavigateToSignUp : SignInSideEffect

    data object NavigateToTermsOfUse : SignInSideEffect

    data object NavigateToPrivacyPolicy : SignInSideEffect

}