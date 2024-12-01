package com.captures2024.soongan.core.model.dto

data class UserInfoDto(
    val user: UserDto,
    val profileImage: String,
) {

    companion object {
        fun defaultBuilder(): UserInfoDto = UserInfoDto(
            user = UserDto.defaultBuilder(),
            profileImage = "",
        )
    }
}
