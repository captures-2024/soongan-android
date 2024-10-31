package com.captures2024.soongan.core.data.repository

import com.captures2024.soongan.core.model.dto.UserInfoDto
import com.captures2024.soongan.core.model.network.SocialSignType
import com.captures2024.soongan.core.model.dto.ResultConditionDto

interface MembersRepository {

    suspend fun withdrawMember(): ResultConditionDto

    suspend fun signOutSocialPlatform(): ResultConditionDto

    suspend fun signingSocialPlatform(
        type: SocialSignType,
        token: String,
        fcmToken: String,
    ): ResultConditionDto

    suspend fun reissueToken(): ResultConditionDto

    suspend fun registerProfileImage()

    suspend fun registerNickname(nickname: String): ResultConditionDto

    suspend fun getMemberInformation(): UserInfoDto

    suspend fun isDuplicateNickname(nickname: String): ResultConditionDto
}
