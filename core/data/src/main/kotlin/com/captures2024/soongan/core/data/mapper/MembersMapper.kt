package com.captures2024.soongan.core.data.mapper

import com.captures2024.soongan.core.model.dto.UserInfoDto
import com.captures2024.soongan.core.model.network.response.members.GetMemberInfoResponse
import com.captures2024.soongan.core.model.network.response.members.PatchBirthYearResponse
import com.captures2024.soongan.core.model.network.response.members.PatchProfileResponse


fun PatchProfileResponse.toUserInfoDto(): UserInfoDto = UserInfoDto(
    email = "",
    nickname = this.nickname,
    selfIntroduction = this.selfIntroduction,
    profileImageUrl = this.profileImageUrl,
)

fun PatchBirthYearResponse.toUserInfoDto(): UserInfoDto = UserInfoDto(
    email = "",
    birthYear = this.birthYear,
)

fun GetMemberInfoResponse.toUserInfoDto(): UserInfoDto = UserInfoDto(
    email = this.email,
    nickname = this.nickname,
    birthYear = this.birthYear,
    profileImageUrl = this.profileImageUrl,
    selfIntroduction = this.selfIntroduction,
)