package com.captures2024.soongan.feature.signIn.state

import com.captures2024.soongan.core.common.base.UIIntent

interface SignInIntent : UIIntent {

    data object OnClickSignApple : SignInIntent

    data object OnClickSignGoogle : SignInIntent

    data object OnClickSignKakao : SignInIntent

    data object CanceledSignApple : SignInIntent

    data object CanceledSignGoogle : SignInIntent

    data object CanceledSignKakao : SignInIntent

    data class CompleteSignApple(
        val token: String
    ) : SignInIntent

    data class CompleteSignGoogle(
        val token: String
    ) : SignInIntent

    data class CompleteSignKakao(
        val accessToken: String,
        val refreshToken: String
    ) : SignInIntent

    data object FailedSignApple : SignInIntent

    data object FailedSignGoogle : SignInIntent

    data object FailedSignKakao : SignInIntent

    data class FetchFCMToken(
        val token: String
    ) : SignInIntent

    data object OnClickTermsOfUse : SignInIntent

    data object OnClickPrivacyPolicy : SignInIntent

    data object OnClickGuestMode : SignInIntent

}