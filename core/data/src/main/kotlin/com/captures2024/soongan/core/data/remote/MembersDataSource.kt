package com.captures2024.soongan.core.data.remote

import com.captures2024.soongan.core.model.dto.UserDto
import com.captures2024.soongan.core.model.dto.UserInfoDto
import com.captures2024.soongan.core.model.network.SocialSignType
import com.captures2024.soongan.core.model.network.response.members.ReissueTokenResponse
import com.captures2024.soongan.core.model.network.response.members.SignInWithTokenResponse

interface MembersDataSource {

    suspend fun registerProfileImage()

    suspend fun registerNickname(
        nickname: String,
    ): UserDto?

    suspend fun getMemberInformation(): UserInfoDto?

    suspend fun isDuplicateNickname(
        nickname: String,
    ): Boolean
}
