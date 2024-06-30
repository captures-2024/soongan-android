package com.captures2024.soongan.initializer

import android.content.Context
import androidx.startup.Initializer
import com.captures2024.soongan.BuildConfig
import com.kakao.sdk.common.KakaoSdk

class KakaoSDKInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        KakaoSdk.init(context, BuildConfig.KAKAO_API_KEY)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}