package com.captures2024.soongan.core.model.dto

data class UserInfoDto(
    val email: String,
    val nickname: String? = null,
    val birthYear: Int? = null,
    val selfIntroduction: String? = null,
    val profileImageUrl: String? = null,
) {

    companion object {
        fun defaultBuilder(): UserInfoDto = UserInfoDto(
            email = "",
        )
    }
}
