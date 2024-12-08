package com.captures2024.soongan.core.data.repository

import com.captures2024.soongan.core.model.dto.ResultConditionDto
import com.captures2024.soongan.core.model.dto.UserInfoDto


interface MembersRepository {

    suspend fun patchProfile(
        nickname: String?,
        selfIntroduction: String?,
        profileImage: String?
    ): UserInfoDto

    suspend fun patchBirthYear(birthYear: Int): UserInfoDto

    suspend fun getMemberInfo(): UserInfoDto

    suspend fun isVerifiedNickname(nickname: String): ResultConditionDto
}
