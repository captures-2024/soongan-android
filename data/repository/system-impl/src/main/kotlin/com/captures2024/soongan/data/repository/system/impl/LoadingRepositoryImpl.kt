package com.captures2024.soongan.data.repository.system.impl

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.domain.repository.system.LoadingRepository
import com.captures2024.soongan.data.source.system.local.LoadingLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoadingRepositoryImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val loadingLocalDataSource: LoadingLocalDataSource,
) : LoadingRepository {
    override val loadingFlow: Flow<Boolean> = loadingLocalDataSource.loadingMap
        .map { it.isNotEmpty() }

    init {
        analyticsHelper.d { "LoadingRepository::init" }
    }

    override fun showLoading(tag: String) {
        loadingLocalDataSource.showLoading(tag)
    }

    override fun hideLoading(tag: String) {
        loadingLocalDataSource.hideLoading(tag)
    }

    override fun clearLoading(tag: String) {
        loadingLocalDataSource.clearLoading(tag)
    }

    override fun isLoading(tag: String): Boolean {
        return loadingLocalDataSource.isLoadingByTag(tag)
    }
}
