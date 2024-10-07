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
                error != null -> callback.onFailure(error = error)
                token != null -> callback.onSuccess(token.accessToken, token.refreshToken)
                else -> callback.onFailure(null)
            }
        }

        when (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            true -> UserApiClient.instance.loginWithKakaoTalk(context, callback = kakaoCallback)
            false -> UserApiClient.instance.loginWithKakaoAccount(context, callback = kakaoCallback)
        }
    }
}
