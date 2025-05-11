package com.captures2024.soongan.core.data.source.system

import kotlinx.coroutines.flow.SharedFlow

interface InAppBrowserLocalDataSource {
    val inAppBrowserUrl: SharedFlow<String>

    suspend fun postInAppBrowserUrl(url: String)
}
