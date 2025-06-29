package com.captures2024.soongan.data.repository.auth.impl

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.model.dto.ResultConditionDto
import com.captures2024.soongan.core.model.utils.SocialSignType
import com.captures2024.soongan.domain.repository.auth.AuthRepository
import com.captures2024.soongan.data.source.auth.remote.AuthRemoteDataSource
import com.captures2024.soongan.data.source.token.local.TokenLocalDataSource
import javax.inject.Inject

class AuthRepositoryImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val tokenLocalDataSource: TokenLocalDataSource,
    private val authRemoteDataSource: AuthRemoteDataSource,
) : AuthRepository {

    init {
        analyticsHelper.d { "AuthRepository::init" }
    }

    override suspend fun withdrawMember(): ResultConditionDto {
        val result = authRemoteDataSource.withdrawWithToken()

        if (result) {
            tokenLocalDataSource.clearAllToken()
        }

        return ResultConditionDto(result = result)
    }

    override suspend fun signOutSocialPlatform(): ResultConditionDto {
        val result = authRemoteDataSource.signOutWithToken()

        if (result) {
            tokenLocalDataSource.clearAllToken()
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

        tokenLocalDataSource.setAccessToken(tokenResult.accessToken)
        tokenLocalDataSource.setRefreshToken(tokenResult.refreshToken)

        val savedAccessToken = tokenLocalDataSource.getAccessToken()
        val savedRefreshToken = tokenLocalDataSource.getRefreshToken()

        return when {
            savedAccessToken == tokenResult.accessToken && savedRefreshToken == tokenResult.refreshToken -> ResultConditionDto(result = true)
            else -> {
                tokenLocalDataSource.clearAllToken()
                ResultConditionDto(result = false)
            }
        }
    }

    override suspend fun reissueToken(): ResultConditionDto {
        val currentAccessToken = tokenLocalDataSource.getAccessToken()
        val currentRefreshToken = tokenLocalDataSource.getRefreshToken()

        if (currentAccessToken.isEmpty() || currentRefreshToken.isEmpty()) {
            return ResultConditionDto(result = false)
        }

        val tokenResult = authRemoteDataSource.reissueToken(
            accessToken = currentAccessToken,
            refreshToken = currentRefreshToken,
        ) ?: return ResultConditionDto(result = false)

        tokenLocalDataSource.setAccessToken(tokenResult.accessToken)
        tokenLocalDataSource.setRefreshToken(tokenResult.refreshToken)

        val savedAccessToken = tokenLocalDataSource.getAccessToken()
        val savedRefreshToken = tokenLocalDataSource.getRefreshToken()

        return when {
            savedAccessToken == tokenResult.accessToken && savedRefreshToken == tokenResult.refreshToken -> ResultConditionDto(result = true)
            else -> {
                tokenLocalDataSource.clearAllToken()
                ResultConditionDto(result = false)
            }
        }
    }
}
