package com.captures2024.soongan.core.data.source.members.local

import com.captures2024.soongan.core.model.dto.ReportHistoryDto
import com.captures2024.soongan.core.model.dto.UserInfoDto
import kotlinx.coroutines.flow.StateFlow

interface MemberLocalDataSource {
    val loginInfo: StateFlow<UserInfoDto?>

    fun updateUserInfo(userInfoDto: UserInfoDto)

    fun updateUserInfo(birthYear: Int)

    fun updateUserInfo(
        nickname: String?,
        selfIntroduction: String?,
        profileImageUrl: String?,
    )

    fun updateUserInfo(histories: List<ReportHistoryDto>)

    fun clearInfo()
}
