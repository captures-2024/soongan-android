package com.captures2024.soongan.data.source.system.local

import kotlinx.coroutines.flow.SharedFlow

interface AppUpdateLocalDataSource {

    val isAppUpdateAvailable: SharedFlow<Boolean>

    suspend fun setIsAppUpdateAvailable(isAppUpdateAvailable: Boolean)
}
