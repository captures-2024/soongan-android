package com.captures2024.soongan.core.viewmodel.intent

import com.captures2024.soongan.core.common.base.UIIntent

sealed interface SignIntent : UIIntent {

    /**
     * SignUIState에 fcmToken을 fetch 해야할 때
     *
     * @property fcmToken fetch 할 fcm Token
     */
    data class FetchFcmToken(
        val fcmToken: String,
    ): SignIntent

    /**
     * 사용자가 게스트 모드로 진입하려고 할 때 발생하는 인텐트
     */
    data object OnClickGuestMode : SignIntent

    /**
     * 사용자가 구글 로그인을 시도할 때 발생하는 인텐트
     */
    data object OnClickSignGoogle : SignIntent

    /**
     * 사용자가 카카오 로그인을 시도할 때 발생하는 인텐트
     */
    data object OnClickSignKakao : SignIntent

    /**
     * 사용자가 이용약관을 확인하려고 할 때 발생하는 인텐트
     */
    data object OnClickTermsOfUse : SignIntent

    /**
     * 사용자가 개인정보 보호정책을 확인하려고 할 때 발생하는 인텐트
     */
    data object OnClickPrivacyPolicy : SignIntent

    /**
     * 사용자가 구글 로그인 과정을 취소했을 때 발생하는 인텐트
     */
    data object CanceledSignGoogle : SignIntent

    /**
     * 사용자가 카카오 로그인 과정을 취소했을 때 발생하는 인텐트
     */
    data object CanceledSignKakao : SignIntent

    /**
     * 구글 로그인이 성공적으로 완료되었을 때 발생하는 인텐트
     *
     * @property token 구글로부터 받은 인증 토큰
     */
    data class CompleteSignGoogle(
        val token: String,
    ) : SignIntent

    /**
     * 카카오 로그인이 성공적으로 완료되었을 때 발생하는 인텐트
     *
     * @property accessToken 카카오로부터 받은 access 토큰
     * @property refreshToken 토큰 갱신을 위해 카카오로부터 받은 refresh 토큰
     */
    data class CompleteSignKakao(
        val accessToken: String,
        val refreshToken: String,
    ) : SignIntent

    /**
     * 구글 로그인 시도가 실패했을 때 발생하는 인텐트
     */
    data object FailedSignGoogle : SignIntent

    /**
     * 카카오 로그인 시도가 실패했을 때 발생하는 인텐트
     */
    data object FailedSignKakao : SignIntent

    /**
     * 사용자 데이터 동기화가 실패했을 때 발생하는 인텐트
     */
    data object FailedSyncData : SignIntent

    /**
     * 사용자 데이터 동기화가 성공적으로 완료되었을 때 발생하는 인텐트
     *
     * @property nickname 사용자의 닉네임 (nullable)
     * @property birthYear 사용자의 출생연도 (nullable)
     */
    data class SuccessSyncData(
        val nickname: String?,
        val birthYear: Int?,
    ) : SignIntent

    /**
     * 사용자의 닉네임과 출생연도 입력이 모두 완료되었을 때 발생하는 인텐트
     *
     * @property nickname 사용자의 닉네임
     * @property birthYear 사용자의 출생연도
     */
    data class SuccessPathBirth(
        val nickname: String,
        val birthYear: Int,
    ) : SignIntent
}