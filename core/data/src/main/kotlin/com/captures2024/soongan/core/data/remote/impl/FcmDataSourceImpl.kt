package com.captures2024.soongan.core.data.remote.impl

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import com.captures2024.soongan.core.data.mapper.toDto
import com.captures2024.soongan.core.data.remote.FcmDataSource
import com.captures2024.soongan.core.data.service.FcmService
import com.captures2024.soongan.core.data.utils.safeAPICall
import com.captures2024.soongan.core.model.dto.FcmDto
import com.captures2024.soongan.core.model.network.request.fcm.InitFcmRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject

class FcmDataSourceImpl
@Inject
constructor(
    @ApplicationContext private val context: Context,
    private val service: FcmService,
) : FcmDataSource {

    @SuppressLint("HardwareIds")
    override suspend fun initFcm(
        fcmToken: String
    ): FcmDto? = safeAPICall {
        Timber.tag("initFcm").d("fcmToken = $fcmToken")
        Timber.tag("initFcm").d("deviceId = ${Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ANDROID_ID
        )}")
        service.initFcm(
            request = InitFcmRequest(
                token = fcmToken,
                deviceId = Settings.Secure.getString(
                    context.contentResolver,
                    Settings.Secure.ANDROID_ID
                )
            )
        )
    }.body?.responseData?.toDto()

}