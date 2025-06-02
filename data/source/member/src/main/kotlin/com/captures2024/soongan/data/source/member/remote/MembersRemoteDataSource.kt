package com.captures2024.soongan.data.source.member.remote

import com.captures2024.soongan.core.model.dto.ResultConditionDto
import com.captures2024.soongan.core.model.dto.UserInfoDto

interface MembersRemoteDataSource {

    suspend fun patchProfile(
        nickname: String? = null,
        selfIntroduction: String? = null,
        profileImageUrl: String? = null,
        isDefaultProfileImage: Boolean = false,
    ): UserInfoDto?

    suspend fun patchBirthYear(
        birthYear: Int,
    ): UserInfoDto?

    suspend fun getMemberInfo(): UserInfoDto?

    suspend fun isVerifiedNickname(
        nickname: String,
    ): ResultConditionDto?
}
