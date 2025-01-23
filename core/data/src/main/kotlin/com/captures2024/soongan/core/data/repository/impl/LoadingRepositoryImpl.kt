package com.captures2024.soongan.core.data.repository.impl

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.data.repository.LoadingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoadingRepositoryImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
) : LoadingRepository {
    private val _isLoading: MutableStateFlow<Map<String, Int>> = MutableStateFlow(emptyMap())
    override val loadingFlow: Flow<Boolean> =  _isLoading.map { it.isNotEmpty() }

    override fun showLoading(tag: String) {
        val currentMap = _isLoading.value.toMutableMap()
        val currentCount = currentMap[tag] ?: 0
        currentMap[tag] = currentCount + 1
        _isLoading.value = currentMap.toMap()

        analyticsHelper.v(message = "showLoading - $tag, ${_isLoading.value}")
    }

    override fun hideLoading(tag: String) {
        val currentMap = _isLoading.value.toMutableMap()
        val currentCount = currentMap[tag] ?: 0

        if (currentCount > 1) {
            currentMap[tag] = currentCount - 1
        } else {
            currentMap.remove(tag)
        }
        _isLoading.value = currentMap.toMap()

        analyticsHelper.v(message = "hideLoading - $tag, ${_isLoading.value}")
    }

    override fun clearLoading(tag: String) {
        _isLoading.value -= tag

        analyticsHelper.v(message = "clearLoading - $tag, ${_isLoading.value}")
    }

    override fun isLoading(tag: String): Boolean = _isLoading.value.contains(tag)
}