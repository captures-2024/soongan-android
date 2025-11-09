package com.captures2024.soongan.data.source.system.impl.local

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.data.source.system.local.AppUpdateLocalDataSource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class AppUpdateLocalDataSourceImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
) : AppUpdateLocalDataSource {

    init {
        analyticsHelper.d { "AppUpdateLocalDataSource::init" }
    }

    private val _isAppUpdateAvailable: MutableSharedFlow<Boolean> = MutableSharedFlow()
    override val isAppUpdateAvailable: SharedFlow<Boolean>
        get() = _isAppUpdateAvailable

    override suspend fun setIsAppUpdateAvailable(isAppUpdateAvailable: Boolean) {
        analyticsHelper.d { "setIsAppUpdateAvailable - isAppUpdateAvailable: $isAppUpdateAvailable" }
        _isAppUpdateAvailable.emit(isAppUpdateAvailable)
    }
}
