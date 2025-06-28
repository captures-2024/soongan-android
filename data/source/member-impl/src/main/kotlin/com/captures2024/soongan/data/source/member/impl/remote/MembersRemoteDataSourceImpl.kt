package com.captures2024.soongan.data.source.member.impl.remote

import android.content.Context
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.model.dto.ResultConditionDto
import com.captures2024.soongan.core.model.dto.UserInfoDto
import com.captures2024.soongan.data.source.member.impl.mapper.toUserInfoDto
import com.captures2024.soongan.data.service.api.MembersService
import com.captures2024.soongan.data.source.member.remote.MembersRemoteDataSource
import com.captures2024.soongan.data.service.api.utils.safeAPICall
import com.captures2024.soongan.data.source.utils.toImageMultiPart
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MembersRemoteDataSourceImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val service: MembersService,
    @ApplicationContext private val context: Context,
) : MembersRemoteDataSource {

    init {
        analyticsHelper.d { "MembersRemoteDataSource::init" }
    }

    override suspend fun patchProfile(
        nickname: String?,
        selfIntroduction: String?,
        profileImageUrl: String?,
        isDefaultProfileImage: Boolean,
    ): UserInfoDto? {
        analyticsHelper.d { "patchProfile - nickname: $nickname, selfIntroduction: $selfIntroduction, profileImageUrl: $profileImageUrl, isDefaultProfileImage: $isDefaultProfileImage" }

        val response = safeAPICall {
            service.patchProfile(
                nickname = nickname,
                selfIntroduction = selfIntroduction,
                profileImageUrl = profileImageUrl.toImageMultiPart(context, "profileImage"),
                isDefaultProfileImage = isDefaultProfileImage,
            )
        }

        val responseHeader = response.headers

        analyticsHelper.d { "patchProfile - responseHeader: $responseHeader" }

        val responseBody = response.body

        analyticsHelper.d { "patchProfile - responseBody: $responseBody" }

        return responseBody?.responseData?.toUserInfoDto()
    }

    override suspend fun patchBirthYear(birthYear: Int): UserInfoDto? {
        analyticsHelper.d { "patchBirthYear - birthYear: $birthYear" }

        val response = safeAPICall { service.patchBirthYear(birthYear = birthYear) }

        val responseHeader = response.headers

        analyticsHelper.d { "patchBirthYear - responseHeader: $responseHeader" }

        val responseBody = response.body

        analyticsHelper.d { "patchBirthYear - responseBody: $responseBody" }

        return responseBody?.responseData?.toUserInfoDto()
    }

    override suspend fun getMemberInfo(): UserInfoDto? {
        analyticsHelper.d { "getMemberInfo - entry" }

        val response = safeAPICall { service.getMemberInfo() }

        val responseHeader = response.headers

        analyticsHelper.d { "getMemberInfo - responseHeader: $responseHeader" }

        val responseBody = response.body

        analyticsHelper.d { "getMemberInfo - responseBody: $responseBody" }

        return responseBody?.responseData?.toUserInfoDto()
    }

    override suspend fun isVerifiedNickname(nickname: String): ResultConditionDto? {
        analyticsHelper.d { "isVerifiedNickname - nickname: $nickname" }

        val response = safeAPICall { service.isVerifiedNickname(nickname) }

        val responseHeader = response.headers

        analyticsHelper.d { "getMemberInfo - responseHeader: $responseHeader" }

        val responseBody = response.body

        analyticsHelper.d { "getMemberInfo - responseBody: $responseBody" }

        return when (val data = responseBody?.responseData) {
            null -> null
            else -> ResultConditionDto(data)
        }
    }
}
