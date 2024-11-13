package com.captures2024.soongan.core.data.repository.impl

import com.captures2024.soongan.core.data.remote.MembersDataSource
import com.captures2024.soongan.core.data.repository.MembersRepository
import com.captures2024.soongan.core.datastore.TokenDataSource
import com.captures2024.soongan.core.model.dto.UserInfoDto
import com.captures2024.soongan.core.model.network.SocialSignType
import com.captures2024.soongan.core.model.dto.ResultConditionDto
import javax.inject.Inject

class MembersRepositoryImpl
@Inject
constructor(
    private val membersDataSource: MembersDataSource,
) : MembersRepository {

    override suspend fun registerProfileImage() {
        TODO("Not yet implemented")
    }

    override suspend fun registerNickname(nickname: String): ResultConditionDto {
        val result = membersDataSource.registerNickname(nickname = nickname) ?: return ResultConditionDto(result = false)

        return when (nickname) {
            result.nickname -> ResultConditionDto(result = true)
            else -> ResultConditionDto(result = false)
        }
    }

    override suspend fun getMemberInformation(): UserInfoDto {
        val userInfoDto = membersDataSource.getMemberInformation() ?: throw NullPointerException("getMemberInformation is null")
        return userInfoDto
    }

    override suspend fun isDuplicateNickname(nickname: String): ResultConditionDto {
        val result = membersDataSource.isDuplicateNickname(nickname)

        return ResultConditionDto(result)
    }
}
