package com.captures2024.soongan.core.data.repository.impl

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.data.source.members.remote.MembersRemoteDataSource
import com.captures2024.soongan.core.data.repository.MembersRepository
import com.captures2024.soongan.core.model.dto.ReportHistoryDto
import com.captures2024.soongan.core.model.dto.ResultConditionDto
import com.captures2024.soongan.core.model.dto.UserInfoDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class MembersRepositoryImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val membersRemoteDataSource: MembersRemoteDataSource,
) : MembersRepository {

    private val _currentMember: MutableStateFlow<UserInfoDto?> = MutableStateFlow(null)
    override val currentMember: StateFlow<UserInfoDto?>
        get() = _currentMember.asStateFlow()

    private val _isGuestMode: MutableStateFlow<Boolean> = MutableStateFlow(false)
    override val isGuestMode: StateFlow<Boolean>
        get() = _isGuestMode.asStateFlow()

    init {
        analyticsHelper.d { "MembersRepository::init" }
    }

    override fun setGuestMode(isGuestMode: Boolean) {
        _isGuestMode.value = isGuestMode
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

        _currentMember.emit(
            value = _currentMember.value
                ?.let { currentMember ->
                    return@let currentMember.copy(
                        nickname = userInfoDto?.nickname,
                        selfIntroduction = userInfoDto?.selfIntroduction,
                        profileImageUrl = userInfoDto?.profileImageUrl,
                    )
                },
        )

        return userInfoDto
            ?.also { analyticsHelper.d { "patchProfile - userInfoDto: $userInfoDto" } }
            ?: throw NullPointerException("userInfoDto is null")
    }

    override suspend fun patchBirthYear(birthYear: Int): UserInfoDto {
        val userInfoDto = membersRemoteDataSource.patchBirthYear(
            birthYear = birthYear,
        )

        _currentMember.emit(
            _currentMember.value?.let { currentMember ->
                return@let currentMember.copy(
                    birthYear = userInfoDto?.birthYear ?: currentMember.birthYear,
                )
            },
        )

        return userInfoDto
            ?.also { analyticsHelper.d { "patchBirthYear - userInfoDto: $userInfoDto" } }
            ?: throw NullPointerException("userInfoDto is null")
    }

    override suspend fun getMemberInfo(): UserInfoDto {
        val userInfoDto = membersRemoteDataSource.getMemberInfo()

        _currentMember.emit(userInfoDto)

        return userInfoDto
            ?.also { analyticsHelper.d { "getMemberInfo - userInfoDto: $userInfoDto" } }
            ?: throw NullPointerException("MemberInfo is null")
    }

    override suspend fun isVerifiedNickname(nickname: String): ResultConditionDto {
        val resultConditionDto = membersRemoteDataSource.isVerifiedNickname(
            nickname = nickname,
        )

        return resultConditionDto
            ?.also { analyticsHelper.d { "isVerifiedNickname - resultConditionDto: $resultConditionDto" } }
            ?: throw NullPointerException("resultConditionDto is null")
    }

    override suspend fun updateReportHistories(histories: List<ReportHistoryDto>) {
        _currentMember.update { currentMember ->
            currentMember?.copy(reportHistories = histories)
        }
    }

    override suspend fun clearCurrentMember() = _currentMember.emit(null)
}
