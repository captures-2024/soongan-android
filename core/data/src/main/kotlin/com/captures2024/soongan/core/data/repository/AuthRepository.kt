package com.captures2024.soongan.core.data.repository

import com.captures2024.soongan.core.model.dto.ResultConditionDto
import com.captures2024.soongan.core.model.network.SocialSignType

interface AuthRepository {

    suspend fun withdrawMember(): ResultConditionDto

    suspend fun signOutSocialPlatform(): ResultConditionDto

    suspend fun signingSocialPlatform(
        type: SocialSignType,
        token: String,
        fcmToken: String,
    ): ResultConditionDto

    suspend fun reissueToken(): ResultConditionDto
}