package com.captures2024.soongan.data.source.member.impl.mapper

import com.captures2024.soongan.core.model.dto.UserInfoDto
import com.captures2024.soongan.core.model.network.response.members.PatchProfileResponse

internal fun PatchProfileResponse.toUserInfoDto(): UserInfoDto = UserInfoDto(
    email = "",
    nickname = this.nickname,
    selfIntroduction = this.selfIntroduction,
    profileImageUrl = this.profileImageUrl,
)
