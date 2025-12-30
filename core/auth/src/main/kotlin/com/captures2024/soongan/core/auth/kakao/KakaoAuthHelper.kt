package com.captures2024.soongan.core.auth.kakao

import android.content.Context

interface KakaoAuthHelper {

    fun kakaoLogin(
        context: Context,
        callback: KakaoLoginCallback,
    )
}
