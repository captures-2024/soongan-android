package com.captures2024.soongan.core.domain.repository

import com.captures2024.soongan.core.model.network.SocialSignType
import com.captures2024.soongan.core.model.entity.ResultConditionEntity

interface MembersRepository {

    suspend fun signingSocialPlatform(
        type: SocialSignType,
        token: String
    ): ResultConditionEntity

}
