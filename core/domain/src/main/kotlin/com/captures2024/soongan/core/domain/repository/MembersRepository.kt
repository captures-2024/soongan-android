package com.captures2024.soongan.core.domain.repository

import com.captures2024.soongan.core.model.network.SocialSignType
import com.captures2024.soongan.core.model.entity.ResultConditionEntity

interface MembersRepository {

    suspend fun withdrawMember(): ResultConditionEntity

    suspend fun signOutSocialPlatform(): ResultConditionEntity

    suspend fun signingSocialPlatform(
        type: SocialSignType,
        token: String
    ): ResultConditionEntity

    suspend fun reissueToken(): ResultConditionEntity

    suspend fun registerProfileImage()

    suspend fun registerNickname(nickname: String): ResultConditionEntity

    suspend fun getMemberInformation()

    suspend fun isDuplicateNickname(nickname: String): ResultConditionEntity

}
