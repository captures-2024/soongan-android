package com.captures2024.soongan.presentation.feature.root.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.captures2024.soongan.core.android.utils.LocalAnalyticsHelper
import com.captures2024.soongan.presentation.feature.root.component.screen.SplashScreen
import com.google.android.gms.tasks.Task
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

@Composable
internal fun AppLandingRoute(onCheckVersion: (Boolean) -> Unit) {
    val context = LocalContext.current
    val analyticsHelper = LocalAnalyticsHelper.current

    LaunchedEffect(Unit) {
        runCatching {
            val appUpdateManager = AppUpdateManagerFactory.create(context)
            val appUpdateInfo = appUpdateManager.appUpdateInfo.await()

            val condition =
                appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
                        appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)

            analyticsHelper.d { "onCheckVersion: condition=$condition" }

            onCheckVersion(condition)
        }.onFailure { exception ->
            analyticsHelper.d { "onCheckVersion: Failed, exception=${exception.message}" }
            onCheckVersion(false)
        }
    }

    SplashScreen()
}

private suspend fun Task<AppUpdateInfo>.await(): AppUpdateInfo {
    return suspendCoroutine { continuation ->
        addOnCompleteListener { result ->
            if (result.isSuccessful) {
                continuation.resume(result.result)
            } else {
                result.exception?.let { continuation.resumeWithException(it) }
            }
        }
    }
}
