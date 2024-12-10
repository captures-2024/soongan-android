package com.captures2024.soongan.core.data.repository.impl

import com.captures2024.soongan.core.data.remote.MembersDataSource
import com.captures2024.soongan.core.data.repository.MembersRepository
import com.captures2024.soongan.core.model.dto.ResultConditionDto
import com.captures2024.soongan.core.model.dto.UserInfoDto
import javax.inject.Inject

class MembersRepositoryImpl
@Inject
constructor(
    private val membersDataSource: MembersDataSource,
) : MembersRepository {

    override suspend fun patchProfile(
        nickname: String?,
        selfIntroduction: String?,
        profileImage: String?
    ): UserInfoDto {
        val userInfoDto = membersDataSource.patchProfile(
            nickname = nickname,
            selfIntroduction = selfIntroduction,
            profileImage = profileImage,
        )

        return userInfoDto ?: throw NullPointerException("userInfoDto is null")
    }

    override suspend fun patchBirthYear(birthYear: Int): UserInfoDto {
        val userInfoDto = membersDataSource.patchBirthYear(
            birthYear = birthYear,
        )

        return userInfoDto ?: throw NullPointerException("userInfoDto is null")
    }

    override suspend fun getMemberInfo(): UserInfoDto {
        val userInfoDto = membersDataSource.getMemberInfo()

        return userInfoDto ?: throw NullPointerException("MemberInfo is null")
    }

    override suspend fun isVerifiedNickname(nickname: String): ResultConditionDto {
        val resultConditionDto = membersDataSource.isVerifiedNickname(
            nickname = nickname,
        )

        return resultConditionDto ?: throw NullPointerException("resultConditionDto is null")
    }
}
