package com.captures2024.soongan.data.source.fcm.impl.local

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.data.source.fcm.local.FcmLocalDataSource
import com.google.firebase.messaging.FirebaseMessaging
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class FcmLocalDataSourceImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
) : FcmLocalDataSource {

    init {
        analyticsHelper.d { "FcmLocalDataSource::init" }
    }

    override suspend fun getFcm(): String = suspendCoroutine { continuation ->
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    val exception =
                        task.exception ?: IllegalStateException("Fail to load FCM token")

                    analyticsHelper.e(exception) { "getFcm - exception: $exception" }

                    continuation.resumeWithException(exception)

                    return@addOnCompleteListener
                }

                val token = task.result

                analyticsHelper.d { "getFcm - token: $token" }

                continuation.resume(token)
            }
    }
}
