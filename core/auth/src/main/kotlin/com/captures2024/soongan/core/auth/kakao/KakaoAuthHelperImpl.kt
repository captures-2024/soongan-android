package com.captures2024.soongan.core.auth.kakao

import android.content.Context
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient

class KakaoAuthHelperImpl : KakaoAuthHelper {

    override fun kakaoLogin(
        context: Context,
        callback: KakaoLoginCallback,
    ) {
        val kakaoCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            when {
                error != null -> callback.onFailureKakaoLogin(error = error)
                token != null -> callback.onSuccessKakaoLogin(token.accessToken, token.refreshToken)
                else -> callback.onFailureKakaoLogin(null)
            }
        }

        when (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            true -> UserApiClient.instance.loginWithKakaoTalk(context, callback = kakaoCallback)
            false -> UserApiClient.instance.loginWithKakaoAccount(context, callback = kakaoCallback)
        }
    }
}
