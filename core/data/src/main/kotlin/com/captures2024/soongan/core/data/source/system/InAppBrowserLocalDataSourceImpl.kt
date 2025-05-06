package com.captures2024.soongan.core.data.source.system

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class InAppBrowserLocalDataSourceImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
): InAppBrowserLocalDataSource {

    private val _inAppBrowserUrl: MutableSharedFlow<String> = MutableSharedFlow()
    override val inAppBrowserUrl: SharedFlow<String>
        get() = _inAppBrowserUrl

    init {
        analyticsHelper.d { "InAppBrowserLocalDataSource::init" }
    }

    override suspend fun postInAppBrowserUrl(url: String) {
        analyticsHelper.d { "postInAppBrowserUrl - url: $url" }
        _inAppBrowserUrl.emit(url)
    }
}
