package com.captures2024.soongan.core.common

object Validation {
    enum class NicknameValidState {
        Success, Length, Regex
    }

    fun isValidNickname(nickname: String): NicknameValidState {
        if (nickname.length !in 3 .. 10) {
            return NicknameValidState.Length
        }

        val regex = "^[a-zA-Z0-9가-힣]+$".toRegex()

        return when (regex.matches(nickname)) {
            true -> NicknameValidState.Success
            false -> NicknameValidState.Regex
        }
    }

}