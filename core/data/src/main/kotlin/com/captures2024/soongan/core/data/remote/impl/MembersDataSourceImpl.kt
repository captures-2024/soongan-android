package com.captures2024.soongan.core.data.remote.impl

import android.content.Context
import com.captures2024.soongan.core.data.mapper.toUserInfoDto
import com.captures2024.soongan.core.data.remote.MembersDataSource
import com.captures2024.soongan.core.data.service.MembersService
import com.captures2024.soongan.core.data.utils.safeAPICall
import com.captures2024.soongan.core.data.utils.toImageMultiPart
import com.captures2024.soongan.core.model.dto.ResultConditionDto
import com.captures2024.soongan.core.model.dto.UserInfoDto
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MembersDataSourceImpl
@Inject
constructor(
    private val service: MembersService,
    @ApplicationContext private val context: Context,
) : MembersDataSource {

    override suspend fun patchProfile(
        nickname: String?,
        selfIntroduction: String?,
        profileImageUrl: String?,
        isDefaultProfileImage: Boolean,
    ): UserInfoDto? = safeAPICall {
        service.patchProfile(
            nickname = nickname,
            selfIntroduction = selfIntroduction,
            profileImageUrl = profileImageUrl.toImageMultiPart(context, "profileImage"),
            isDefaultProfileImage = isDefaultProfileImage
        )
    }.body?.responseData?.toUserInfoDto()

    override suspend fun patchBirthYear(birthYear: Int): UserInfoDto? = safeAPICall {
        service.patchBirthYear(birthYear = birthYear)
    }.body?.responseData?.toUserInfoDto()

    override suspend fun getMemberInfo(): UserInfoDto? = safeAPICall {
        service.getMemberInfo()
    }.body?.responseData?.toUserInfoDto()

    override suspend fun isVerifiedNickname(nickname: String): ResultConditionDto? {
        val data = safeAPICall {
            service.isVerifiedNickname(nickname)
        }.body?.responseData

        if (data == null) {
            return null
        }

        return ResultConditionDto(data)
    }
}
