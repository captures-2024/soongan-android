package com.captures2024.soongan.data.source.system.local

import kotlinx.coroutines.flow.SharedFlow

interface InAppBrowserLocalDataSource {
    val inAppBrowserUrl: SharedFlow<String>

    suspend fun postInAppBrowserUrl(url: String)
}
