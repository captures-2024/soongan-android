package com.captures2024.soongan.data.repository.system.impl

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.data.repository.system.SystemRepository
import com.captures2024.soongan.data.source.system.local.InAppBrowserLocalDataSource
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class SystemRepositoryImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val inAppBrowserLocalDataSource: InAppBrowserLocalDataSource,
) : SystemRepository {
    override val inAppBrowserUrl: SharedFlow<String>
        get() = inAppBrowserLocalDataSource.inAppBrowserUrl

    init {
        analyticsHelper.d { "SystemRepository::init" }
    }

    override suspend fun launchInAppBrowser(url: String) {
        inAppBrowserLocalDataSource.postInAppBrowserUrl(url)
    }
}
