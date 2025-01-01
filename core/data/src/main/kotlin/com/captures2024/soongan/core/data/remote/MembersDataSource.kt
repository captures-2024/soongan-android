package com.captures2024.soongan.core.data.remote

import com.captures2024.soongan.core.model.dto.ResultConditionDto
import com.captures2024.soongan.core.model.dto.UserInfoDto

interface MembersDataSource {

    suspend fun patchProfile(
        nickname: String? = null,
        selfIntroduction: String? = null,
        profileImage: String? = null
    ): UserInfoDto?

    suspend fun patchBirthYear(
        birthYear: Int,
    ): UserInfoDto?

    suspend fun getMemberInfo(): UserInfoDto?

    suspend fun isVerifiedNickname(
        nickname: String,
    ): ResultConditionDto?
}
