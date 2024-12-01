package com.captures2024.soongan.feature.signIn.state

import com.captures2024.soongan.core.common.base.UIIntent

sealed interface SignInIntent : UIIntent {

    data class FetchFcmToken(
        val fcmToken: String,
    ): SignInIntent

    data object OnClickSignGoogle : SignInIntent

    data object OnClickSignKakao : SignInIntent

    data object OnClickTermsOfUse : SignInIntent

    data object OnClickPrivacyPolicy : SignInIntent

    data object CanceledSignGoogle : SignInIntent

    data object CanceledSignKakao : SignInIntent

    data class CompleteSignGoogle(
        val token: String,
    ) : SignInIntent

    data class CompleteSignKakao(
        val accessToken: String,
        val refreshToken: String,
    ) : SignInIntent

    data object FailedSignGoogle : SignInIntent

    data object FailedSignKakao : SignInIntent

    data object FailedSyncData : SignInIntent

    data class SuccessSyncData(
        val nickname: String,
        val birthDate: String,
    ) : SignInIntent
}