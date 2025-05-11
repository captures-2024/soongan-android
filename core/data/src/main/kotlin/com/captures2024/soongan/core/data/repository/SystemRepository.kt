package com.captures2024.soongan.core.data.repository

import kotlinx.coroutines.flow.SharedFlow

interface SystemRepository {
    val inAppBrowserUrl: SharedFlow<String>

    suspend fun launchInAppBrowser(url: String)
}
