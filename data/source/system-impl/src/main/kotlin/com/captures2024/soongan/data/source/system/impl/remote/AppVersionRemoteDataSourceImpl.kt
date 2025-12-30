package com.captures2024.soongan.data.source.system.impl.remote

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.data.source.system.remote.AppVersionRemoteDataSource
import javax.inject.Inject

class AppVersionRemoteDataSourceImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    // TODO(Implement AppVersion API)
) : AppVersionRemoteDataSource {
    override suspend fun getAppVersion(): String {
        analyticsHelper.d { "getAppVersion - entry" }

        return ""
    }
}
