package com.captures2024.soongan.core.data.repository

import com.captures2024.soongan.core.datastore.TokenDataSource
import com.captures2024.soongan.core.domain.repository.TokenRepository
import javax.inject.Inject

class TokenRepositoryImpl
@Inject
constructor(
    private val tokenDataStore: TokenDataSource
) : TokenRepository {

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