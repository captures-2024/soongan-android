package com.captures2024.soongan.core.auth.kakao

interface KakaoLoginCallback {

    fun onSuccessKakaoLogin(
        accessToken: String?,
        refreshToken: String?,
    )

    fun onFailureKakaoLogin(error: Throwable?)
}
