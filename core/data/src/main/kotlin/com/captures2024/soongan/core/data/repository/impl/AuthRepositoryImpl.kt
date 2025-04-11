package com.captures2024.soongan.core.data.repository.impl

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.data.remote.AuthDataSource
import com.captures2024.soongan.core.data.repository.AuthRepository
import com.captures2024.soongan.core.datastore.TokenDataSource
import com.captures2024.soongan.core.model.dto.ResultConditionDto
import com.captures2024.soongan.core.model.utils.SocialSignType
import javax.inject.Inject

class AuthRepositoryImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val tokenDataSource: TokenDataSource,
    private val authDataSource: AuthDataSource,
) : AuthRepository {

    init {
        analyticsHelper.d { "AuthRepository::init" }
    }

    override suspend fun withdrawMember(): ResultConditionDto = when (authDataSource.withdrawWithToken()) {
        true -> {
            tokenDataSource.clearAllToken()
            ResultConditionDto(result = true)
        }
        false -> ResultConditionDto(result = false)
    }

    override suspend fun signOutSocialPlatform(): ResultConditionDto = when (authDataSource.signOutWithToken()) {
        true -> {
            tokenDataSource.clearAllToken()
            ResultConditionDto(result = true)
        }
        false -> ResultConditionDto(result = false)
    }

    override suspend fun signingSocialPlatform(
        type: SocialSignType,
        token: String,
        fcmToken: String,
    ): ResultConditionDto {
        val tokenResult = authDataSource.signInWithToken(
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

        val tokenResult = authDataSource.reissueToken(
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
