package com.captures2024.soongan.data.source.member.impl.mapper

import com.captures2024.soongan.core.model.dto.UserInfoDto
import com.captures2024.soongan.core.model.network.response.members.GetMemberInfoResponse

internal fun GetMemberInfoResponse.toUserInfoDto(): UserInfoDto = UserInfoDto(
    email = this.email,
    nickname = this.nickname,
    birthYear = this.birthYear,
    profileImageUrl = this.profileImageUrl,
    selfIntroduction = this.selfIntroduction,
    reportHistories = this.reportHistories.map { it.toReportHistoryDto() },
)
