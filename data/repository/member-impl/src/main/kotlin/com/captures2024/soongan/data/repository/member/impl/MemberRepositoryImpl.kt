package com.captures2024.soongan.data.repository.member.impl

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.model.dto.ReportHistoryDto
import com.captures2024.soongan.core.model.dto.ResultConditionDto
import com.captures2024.soongan.core.model.dto.UserInfoDto
import com.captures2024.soongan.data.source.member.local.GuestLocalDataSource
import com.captures2024.soongan.data.source.member.local.MemberLocalDataSource
import com.captures2024.soongan.data.source.member.remote.MembersRemoteDataSource
import com.captures2024.soongan.domain.repository.member.MemberRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MemberRepositoryImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val guestLocalDataSource: GuestLocalDataSource,
    private val memberLocalDataSource: MemberLocalDataSource,
    private val membersRemoteDataSource: MembersRemoteDataSource,
) : MemberRepository {

    override val currentMember: StateFlow<UserInfoDto?>
        get() = memberLocalDataSource.loginInfo

    override val isGuestMode: StateFlow<Boolean>
        get() = guestLocalDataSource.isGuestMode

    init {
        analyticsHelper.d { "MembersRepository::init" }
    }

    override fun setGuestMode(isGuestMode: Boolean) {
        guestLocalDataSource.setGuestMode(isGuestMode)
    }

    override suspend fun patchProfile(
        nickname: String?,
        selfIntroduction: String?,
        profileImageUrl: String?,
        isDefaultProfileImage: Boolean,
    ): UserInfoDto {
        val userInfoDto = membersRemoteDataSource.patchProfile(
            nickname = nickname,
            selfIntroduction = selfIntroduction,
            profileImageUrl = profileImageUrl,
            isDefaultProfileImage = isDefaultProfileImage,
        )

        if (userInfoDto == null) {
            throw NullPointerException("userInfoDto is null")
        }

        memberLocalDataSource.updateUserInfo(
            nickname = nickname,
            selfIntroduction = selfIntroduction,
            profileImageUrl = profileImageUrl,
        )

        return userInfoDto
    }

    override suspend fun patchBirthYear(birthYear: Int): UserInfoDto {
        val userInfoDto = membersRemoteDataSource.patchBirthYear(birthYear = birthYear)

        if (userInfoDto == null) {
            throw NullPointerException("userInfoDto is null")
        }

        memberLocalDataSource.updateUserInfo(birthYear = birthYear)

        return userInfoDto
    }

    override suspend fun getMemberInfo(): UserInfoDto {
        val userInfoDto = membersRemoteDataSource.getMemberInfo()

        if (userInfoDto == null) {
            throw NullPointerException("userInfoDto is null")
        }

        memberLocalDataSource.updateUserInfo(userInfoDto)

        return userInfoDto
    }

    override suspend fun isVerifiedNickname(nickname: String): ResultConditionDto {
        val resultConditionDto = membersRemoteDataSource.isVerifiedNickname(nickname = nickname)

        if (resultConditionDto == null) {
            throw NullPointerException("resultConditionDto is null")
        }

        return resultConditionDto
    }

    override suspend fun updateReportHistories(histories: List<ReportHistoryDto>) {
        memberLocalDataSource.updateUserInfo(histories = histories)
    }

    override suspend fun clearCurrentMember() {
        memberLocalDataSource.clearInfo()
    }
}
