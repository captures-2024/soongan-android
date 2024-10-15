package com.captures2024.soongan.core.common

object Validation {
    enum class NicknameValidState {
        Success, Length, Regex
    }

    fun isValidNickname(nickname: String): NicknameValidState {
        if (nickname.length !in 3..10) {
            return NicknameValidState.Length
        }

        val regex = "^[a-zA-Z0-9가-힣]+$".toRegex()

        return when (regex.matches(nickname)) {
            true -> NicknameValidState.Success
            false -> NicknameValidState.Regex
        }
    }

    enum class BirthYearValidState {
        Success, Length, Regex
    }

    fun isValidBirthYear(birthYear: String): BirthYearValidState {
        if (birthYear.length in 0 until 4) {
            return BirthYearValidState.Length
        }

        val num = birthYear.toIntOrNull() ?: return BirthYearValidState.Regex

        if (num !in 1950..2009) {
            return BirthYearValidState.Regex
        }

        return BirthYearValidState.Success
    }
}
