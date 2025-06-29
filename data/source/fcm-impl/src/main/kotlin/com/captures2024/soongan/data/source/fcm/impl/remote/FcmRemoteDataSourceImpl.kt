package com.captures2024.soongan.data.source.fcm.impl.remote

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.model.dto.FcmDto
import com.captures2024.soongan.core.model.network.request.fcm.InitFcmRequest
import com.captures2024.soongan.data.service.api.FcmAPI
import com.captures2024.soongan.data.service.api.utils.safeAPICall
import com.captures2024.soongan.data.source.fcm.impl.mapper.toDto
import com.captures2024.soongan.data.source.fcm.remote.FcmRemoteDataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class FcmRemoteDataSourceImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    @param:ApplicationContext private val context: Context,
    private val fcmAPI: FcmAPI,
) : FcmRemoteDataSource {

    init {
        analyticsHelper.d { "FcmRemoteDataSource::init" }
    }

    @SuppressLint("HardwareIds")
    override suspend fun initFcm(fcmToken: String): FcmDto? {
        analyticsHelper.d { "initFcm - fcmToken: $fcmToken" }

        val response = safeAPICall {
            fcmAPI.initFcm(
                request = InitFcmRequest(
                    token = fcmToken,
                    deviceId = Settings.Secure.getString(
                        context.contentResolver,
                        Settings.Secure.ANDROID_ID,
                    ),
                ),
            )
        }

        val responseHeader = response.headers

        analyticsHelper.d { "initFcm - responseHeader: $responseHeader" }

        val responseBody = response.body

        analyticsHelper.d { "initFcm - responseBody: $responseBody" }

        return responseBody?.responseData?.toDto()
    }
}
