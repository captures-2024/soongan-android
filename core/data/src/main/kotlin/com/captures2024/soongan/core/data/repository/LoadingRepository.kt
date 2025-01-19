package com.captures2024.soongan.core.data.repository

import kotlinx.coroutines.flow.Flow

interface LoadingRepository {
    val loadingFlow: Flow<Boolean>

    fun showLoading(tag: String)

    fun hideLoading(tag: String)

    fun clearLoading(tag: String)

    fun isLoading(tag: String): Boolean
}