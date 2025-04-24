package com.captures2024.soongan.core.data.source.ui.local

import kotlinx.coroutines.flow.StateFlow

interface LoadingLocalDataSource {
    val loadingMap: StateFlow<Map<String, Int>>

    fun showLoading(tag: String)

    fun hideLoading(tag: String)

    fun clearLoading(tag: String)

    fun isLoadingByTag(tag: String): Boolean
}
