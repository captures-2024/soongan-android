package com.captures2024.soongan.data.repository.token.impl

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.data.repository.token.TokenRepository
import com.captures2024.soongan.data.source.token.local.TokenLocalDataSource
import javax.inject.Inject

class TokenRepositoryImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val tokenDataStore: TokenLocalDataSource,
) : TokenRepository {

    init {
        analyticsHelper.d { "TokenRepository::init" }
    }

    override suspend fun setAccessToken(accessToken: String) {
        tokenDataStore.setAccessToken(accessToken)
    }

    override suspend fun setRefreshToken(refreshToken: String) {
        tokenDataStore.setRefreshToken(refreshToken)
    }

    override suspend fun setUUID(uuid: Long) {
        tokenDataStore.setUUID(uuid)
    }

    override suspend fun getAccessToken(): String = tokenDataStore.getAccessToken()

    override suspend fun getRefreshToken(): String = tokenDataStore.getRefreshToken()

    override suspend fun getUUID(): Long = tokenDataStore.getUUID()

    override suspend fun clearAccessToken() {
        tokenDataStore.clearAccessToken()
    }

    override suspend fun clearRefreshToken() {
        tokenDataStore.clearRefreshToken()
    }

    override suspend fun clearAllToken() {
        tokenDataStore.clearAllToken()
    }
}
