package com.captures2024.soongan.core.model.dto

data class UserDto(
    val email: String,
    val nickname: String,
    val birthDate: String,
) {

    companion object {
        fun defaultBuilder(): UserDto = UserDto(
            email = "",
            nickname = "",
            birthDate = ""
        )
    }
}
