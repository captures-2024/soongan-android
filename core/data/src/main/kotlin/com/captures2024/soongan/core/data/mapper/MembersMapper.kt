package com.captures2024.soongan.core.data.mapper

import com.captures2024.soongan.core.model.dto.UserDto
import com.captures2024.soongan.core.model.dto.UserInfoDto
import com.captures2024.soongan.core.model.network.response.members.GetMemberInformationResponse

internal fun GetMemberInformationResponse.toUserInfoDto() = UserInfoDto(
    user = UserDto(
        email = this.email,
        nickname = this.nickname ?: "",
        birthDate = this.birthDate ?: ""
    ),
    profileImage = this.profileImage ?: ""
)