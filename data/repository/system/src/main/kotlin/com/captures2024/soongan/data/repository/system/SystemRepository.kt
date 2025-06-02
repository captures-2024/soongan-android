package com.captures2024.soongan.data.repository.system

import kotlinx.coroutines.flow.SharedFlow

interface SystemRepository {
    val inAppBrowserUrl: SharedFlow<String>

    suspend fun launchInAppBrowser(url: String)
}
