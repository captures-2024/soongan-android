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
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class FcmDataSourceImpl
@Inject
constructor(
    @ApplicationContext private val context: Context,
    private val service: FcmService,
) : FcmDataSource {

    @SuppressLint("HardwareIds")
    override suspend fun initFcm(): FcmDto? = safeAPICall {
        service.initFcm(
            request = InitFcmRequest(
                token = getFcm(),
                deviceId = Settings.Secure.getString(
                    context.contentResolver,
                    Settings.Secure.ANDROID_ID,
                ),
            ),
        )
    }.body?.responseData?.toDto()

    override suspend fun getFcm(): String = suspendCoroutine { continuation ->
        FirebaseMessaging.getInstance()
            .token
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    continuation.resumeWithException(
                        task.exception ?: IllegalStateException("Fail to load FCM token"),
                    )

                    return@addOnCompleteListener
                }

                val token = task.result

                continuation.resume(token)
            }
    }
}
