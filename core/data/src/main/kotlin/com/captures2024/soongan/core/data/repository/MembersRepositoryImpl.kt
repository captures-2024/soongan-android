package com.captures2024.soongan.core.data.repository

import com.captures2024.soongan.core.data.remote.MembersDataSource
import com.captures2024.soongan.core.datastore.TokenDataSource
import com.captures2024.soongan.core.domain.repository.MembersRepository
import com.captures2024.soongan.core.model.SocialSignType
import com.captures2024.soongan.core.model.entity.ResultConditionEntity
import javax.inject.Inject

class MembersRepositoryImpl
@Inject
constructor(
    private val tokenDataSource: TokenDataSource,
    private val membersDataSource: MembersDataSource
) : MembersRepository {
    override suspend fun signingSocialPlatform(
        type: SocialSignType,
        token: String
    ): ResultConditionEntity {
        val tokenResult = membersDataSource.signWithToken(type, token) ?: return ResultConditionEntity(result = false)

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
}