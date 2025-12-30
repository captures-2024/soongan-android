package com.captures2024.soongan.data.source.member.impl.mapper

import com.captures2024.soongan.core.model.dto.UserInfoDto
import com.captures2024.soongan.core.model.network.response.members.PatchBirthYearResponse

internal fun PatchBirthYearResponse.toUserInfoDto(): UserInfoDto = UserInfoDto(
    email = "",
    birthYear = this.birthYear,
)
