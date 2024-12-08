package com.captures2024.soongan.core.viewmodel.intent

import com.captures2024.soongan.core.common.base.UIIntent

sealed interface SignIntent : UIIntent {

    data class FetchFcmToken(
        val fcmToken: String,
    ): SignIntent

    data object OnClickGuestMode : SignIntent

    data object OnClickSignGoogle : SignIntent

    data object OnClickSignKakao : SignIntent

    data object OnClickTermsOfUse : SignIntent

    data object OnClickPrivacyPolicy : SignIntent

    data object CanceledSignGoogle : SignIntent

    data object CanceledSignKakao : SignIntent

    data class CompleteSignGoogle(
        val token: String,
    ) : SignIntent

    data class CompleteSignKakao(
        val accessToken: String,
        val refreshToken: String,
    ) : SignIntent

    data object FailedSignGoogle : SignIntent

    data object FailedSignKakao : SignIntent

    data object FailedSyncData : SignIntent

    data class SuccessSyncData(
        val nickname: String?,
        val birthYear: Int?,
    ) : SignIntent

    data class SuccessPathBirth(
        val nickname: String,
        val birthYear: Int,
    ) : SignIntent
}