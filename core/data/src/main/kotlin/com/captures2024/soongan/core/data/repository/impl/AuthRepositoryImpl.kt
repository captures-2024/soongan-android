package com.captures2024.soongan.core.data.repository.impl

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.data.source.auth.remote.AuthRemoteDataSource
import com.captures2024.soongan.core.data.repository.AuthRepository
import com.captures2024.soongan.data.datastore.TokenDataSource
import com.captures2024.soongan.core.model.dto.ResultConditionDto
import com.captures2024.soongan.core.model.utils.SocialSignType
import javax.inject.Inject

class AuthRepositoryImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val tokenDataSource: TokenDataSource,
    private val authRemoteDataSource: AuthRemoteDataSource,
) : AuthRepository {

    init {
        analyticsHelper.d { "AuthRepository::init" }
    }

    override suspend fun withdrawMember(): ResultConditionDto {
        val result = authRemoteDataSource.withdrawWithToken()

        if (result) {
            tokenDataSource.clearAllToken()
        }

        return ResultConditionDto(result = result)
    }

    override suspend fun signOutSocialPlatform(): ResultConditionDto {
        val result = authRemoteDataSource.signOutWithToken()

        if (result) {
            tokenDataSource.clearAllToken()
        }

        return ResultConditionDto(result = result)
    }

    override suspend fun signingSocialPlatform(
        type: SocialSignType,
        token: String,
        fcmToken: String,
    ): ResultConditionDto {
        val tokenResult = authRemoteDataSource.signInWithToken(
            type = type,
            token = token,
            fcmToken = fcmToken,
        ) ?: return ResultConditionDto(result = false)

        tokenDataSource.setAccessToken(tokenResult.accessToken)
        tokenDataSource.setRefreshToken(tokenResult.refreshToken)

        val savedAccessToken = tokenDataSource.getAccessToken()
        val savedRefreshToken = tokenDataSource.getRefreshToken()

        return when {
            savedAccessToken == tokenResult.accessToken && savedRefreshToken == tokenResult.refreshToken -> ResultConditionDto(result = true)
            else -> {
                tokenDataSource.clearAllToken()
                ResultConditionDto(result = false)
            }
        }
    }

    override suspend fun reissueToken(): ResultConditionDto {
        val currentAccessToken = tokenDataSource.getAccessToken()
        val currentRefreshToken = tokenDataSource.getRefreshToken()

        if (currentAccessToken.isEmpty() || currentRefreshToken.isEmpty()) {
            return ResultConditionDto(result = false)
        }

        val tokenResult = authRemoteDataSource.reissueToken(
            accessToken = currentAccessToken,
            refreshToken = currentRefreshToken,
        ) ?: return ResultConditionDto(result = false)

        tokenDataSource.setAccessToken(tokenResult.accessToken)
        tokenDataSource.setRefreshToken(tokenResult.refreshToken)

        val savedAccessToken = tokenDataSource.getAccessToken()
        val savedRefreshToken = tokenDataSource.getRefreshToken()

        return when {
            savedAccessToken == tokenResult.accessToken && savedRefreshToken == tokenResult.refreshToken -> ResultConditionDto(result = true)
            else -> {
                tokenDataSource.clearAllToken()
                ResultConditionDto(result = false)
            }
        }
    }
}
