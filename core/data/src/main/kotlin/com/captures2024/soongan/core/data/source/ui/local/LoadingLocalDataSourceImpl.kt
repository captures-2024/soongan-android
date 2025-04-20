package com.captures2024.soongan.core.data.source.ui.local

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import kotlin.collections.minus

class LoadingLocalDataSourceImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
) : LoadingLocalDataSource {

    private val _loadingMap: MutableStateFlow<Map<String, Int>> = MutableStateFlow(emptyMap())
    override val loadingMap: StateFlow<Map<String, Int>>
        get() = _loadingMap.asStateFlow()

    init {
        analyticsHelper.d { "LoadingLocalDataSource::init" }
    }

    override fun showLoading(tag: String) {
        val currentMap = _loadingMap.value.toMutableMap()
        val currentCount = currentMap[tag] ?: 0
        currentMap[tag] = currentCount + 1
        _loadingMap.value = currentMap.toMap()

        analyticsHelper.i { "showLoading - $tag, ${_loadingMap.value}" }
    }

    override fun hideLoading(tag: String) {
        val currentMap = _loadingMap.value.toMutableMap()
        val currentCount = currentMap[tag] ?: 0

        if (currentCount > 1) {
            currentMap[tag] = currentCount - 1
        } else {
            currentMap.remove(tag)
        }
        _loadingMap.value = currentMap.toMap()

        analyticsHelper.i { "hideLoading - $tag, ${_loadingMap.value}" }
    }

    override fun clearLoading(tag: String) {
        _loadingMap.value -= tag

        analyticsHelper.i { "clearLoading - $tag, ${_loadingMap.value}" }
    }

    override fun isLoadingByTag(tag: String): Boolean {
        return _loadingMap.value.contains(tag)
    }
}
