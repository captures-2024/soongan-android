package com.captures2024.soongan.data.source.system.remote

interface AppVersionRemoteDataSource {

    suspend fun getAppVersion(): String
}
