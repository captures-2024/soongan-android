package com.captures2024.soongan.core.auth.kakao

interface KakaoLoginCallback {

    fun onSuccess(
        accessToken: String?,
        refreshToken: String?,
    )

    fun onFailure(error: Throwable?)
}
