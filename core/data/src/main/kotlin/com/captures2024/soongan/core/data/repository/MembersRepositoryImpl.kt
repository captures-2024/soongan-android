package com.captures2024.soongan.core.data.repository

import com.captures2024.soongan.core.data.remote.MembersDataSource
import com.captures2024.soongan.core.datastore.TokenDataSource
import com.captures2024.soongan.core.domain.repository.MembersRepository
import com.captures2024.soongan.core.model.dto.UserInfoDto
import com.captures2024.soongan.core.model.network.SocialSignType
import com.captures2024.soongan.core.model.entity.ResultConditionEntity
import timber.log.Timber
import javax.inject.Inject

class MembersRepositoryImpl
@Inject
constructor(
    private val tokenDataSource: TokenDataSource,
    private val membersDataSource: MembersDataSource
) : MembersRepository {
    override suspend fun withdrawMember(): ResultConditionEntity = when (membersDataSource.withdrawWithToken()) {
        true -> {
            tokenDataSource.clearAllToken()
            ResultConditionEntity(result = true)
        }
        false -> ResultConditionEntity(result = false)
    }

    override suspend fun signOutSocialPlatform(): ResultConditionEntity = when (membersDataSource.signOutWithToken()) {
        true -> {
            tokenDataSource.clearAllToken()
            ResultConditionEntity(result = true)
        }
        false -> ResultConditionEntity(result = false)
    }

    override suspend fun signingSocialPlatform(
        type: SocialSignType,
        token: String
    ): ResultConditionEntity {
        val tokenResult = membersDataSource.signInWithToken(type, token) ?: return ResultConditionEntity(result = false)

        tokenDataSource.setAccessToken(tokenResult.accessToken)
        tokenDataSource.setRefreshToken(tokenResult.refreshToken)

        val savedAccessToken = tokenDataSource.getAccessToken()
        val savedRefreshToken = tokenDataSource.getRefreshToken()

        return when {
            savedAccessToken == tokenResult.accessToken && savedRefreshToken == tokenResult.refreshToken -> ResultConditionEntity(result = true)
            else -> {
                tokenDataSource.clearAllToken()
                ResultConditionEntity(result = false)
            }
        }
    }

    override suspend fun reissueToken(): ResultConditionEntity {
        val currentAccessToken = tokenDataSource.getAccessToken()
        val currentRefreshToken = tokenDataSource.getRefreshToken()

        if (currentAccessToken.isEmpty() || currentRefreshToken.isEmpty()) {
            return ResultConditionEntity(result = false)
        }

        val tokenResult = membersDataSource.reissueToken(
            accessToken = currentAccessToken,
            refreshToken = currentRefreshToken
        ) ?: return ResultConditionEntity(result = false)

        tokenDataSource.setAccessToken(tokenResult.accessToken)
        tokenDataSource.setRefreshToken(tokenResult.refreshToken)

        val savedAccessToken = tokenDataSource.getAccessToken()
        val savedRefreshToken = tokenDataSource.getRefreshToken()

        return when {
            savedAccessToken == tokenResult.accessToken && savedRefreshToken == tokenResult.refreshToken -> ResultConditionEntity(result = true)
            else -> {
                tokenDataSource.clearAllToken()
                ResultConditionEntity(result = false)
            }
        }
    }

    override suspend fun registerProfileImage() {
        TODO("Not yet implemented")
    }

    override suspend fun registerNickname(nickname: String): ResultConditionEntity {
        val result = membersDataSource.registerNickname(nickname = nickname) ?: return ResultConditionEntity(result = false)
        TODO("Not yet implemented")
    }

    override suspend fun getMemberInformation(): UserInfoDto {
        val userInfoDto = membersDataSource.getMemberInformation() ?: throw NullPointerException()

        Timber.tag("getMemberInformation").d("userInfoDto = $userInfoDto")

        return userInfoDto
    }

    override suspend fun isDuplicateNickname(nickname: String): ResultConditionEntity {
        val result = membersDataSource.isDuplicateNickname(nickname)

        return ResultConditionEntity(result)
    }

}