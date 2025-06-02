package com.captures2024.soongan.data.source.token.local

interface TokenLocalDataSource {

    suspend fun setAccessToken(accessToken: String)

    suspend fun setRefreshToken(refreshToken: String)

    suspend fun setUUID(uuid: Long)

    suspend fun getAccessToken(): String

    suspend fun getRefreshToken(): String

    suspend fun getUUID(): Long

    suspend fun clearAccessToken()

    suspend fun clearRefreshToken()

    suspend fun clearAllToken()
}