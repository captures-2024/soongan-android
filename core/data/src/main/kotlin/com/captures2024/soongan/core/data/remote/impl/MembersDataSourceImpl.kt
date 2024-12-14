package com.captures2024.soongan.core.data.remote.impl

import com.captures2024.soongan.core.data.mapper.toUserInfoDto
import com.captures2024.soongan.core.data.remote.MembersDataSource
import com.captures2024.soongan.core.data.service.MembersService
import com.captures2024.soongan.core.data.utils.safeAPICall
import com.captures2024.soongan.core.model.dto.ResultConditionDto
import com.captures2024.soongan.core.model.dto.UserInfoDto
import okhttp3.MediaType
import okhttp3.RequestBody
import javax.inject.Inject

class MembersDataSourceImpl
@Inject
constructor(
    private val service: MembersService,
) : MembersDataSource {

    override suspend fun patchProfile(
        nickname: String?,
        selfIntroduction: String?,
        profileImage: String?,
    ): UserInfoDto? = safeAPICall {
        service.patchProfile(
            nickname = nickname?.let { RequestBody.create(MediaType.parse("text/plain"), nickname) },
            selfIntroduction = selfIntroduction?.let { RequestBody.create(MediaType.parse("text/plain"), selfIntroduction) },
            profileImage = null,
//            profileImage = MultipartBody.Part.createFormData("profileImage", imageFile.name, imageRequestBody),
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
