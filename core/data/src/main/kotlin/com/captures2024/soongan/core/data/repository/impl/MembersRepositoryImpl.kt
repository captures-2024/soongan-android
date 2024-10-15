package com.captures2024.soongan.core.data.repository.impl

import com.captures2024.soongan.core.data.remote.MembersDataSource
import com.captures2024.soongan.core.data.repository.MembersRepository
import com.captures2024.soongan.core.datastore.TokenDataSource
import com.captures2024.soongan.core.model.dto.UserInfoDto
import com.captures2024.soongan.core.model.network.SocialSignType
import com.captures2024.soongan.core.model.dto.ResultConditionDto
import javax.inject.Inject

class MembersRepositoryImpl
@Inject
constructor(
    private val tokenDataSource: TokenDataSource,
    private val membersDataSource: MembersDataSource,
) : MembersRepository {
    override suspend fun withdrawMember(): ResultConditionDto = when (membersDataSource.withdrawWithToken()) {
        true -> {
            tokenDataSource.clearAllToken()
            ResultConditionDto(result = true)
        }
        false -> ResultConditionDto(result = false)
    }

    override suspend fun signOutSocialPlatform(): ResultConditionDto = when (membersDataSource.signOutWithToken()) {
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
        val tokenResult = membersDataSource.signInWithToken(
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

        val tokenResult = membersDataSource.reissueToken(
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

    override suspend fun registerProfileImage() {
        TODO("Not yet implemented")
    }

    override suspend fun registerNickname(nickname: String): ResultConditionDto {
        val result = membersDataSource.registerNickname(nickname = nickname) ?: return ResultConditionDto(result = false)

        return when (nickname) {
            result.nickname -> ResultConditionDto(result = true)
            else -> ResultConditionDto(result = false)
        }
    }

    override suspend fun getMemberInformation(): UserInfoDto {
        val userInfoDto = membersDataSource.getMemberInformation() ?: throw NullPointerException("getMemberInformation is null")
        return userInfoDto
    }

    override suspend fun isDuplicateNickname(nickname: String): ResultConditionDto {
        val result = membersDataSource.isDuplicateNickname(nickname)

        return ResultConditionDto(result)
    }
}
