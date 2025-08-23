package com.captures2024.soongan.presentation.viewmodel.model

data class UserProfile(
    val nickname: String,
    val selfIntroduction: String,
    val profileImageUrl: String?,
) {

    companion object {
        const val DEFAULT_SELF_INTRODUCTION = "본인을 소개해주세요"

        val guestUserProfile: UserProfile = UserProfile(
            nickname = "로그인이 필요해요",
            selfIntroduction = "로그인 후 본인을 소개해주세요",
            profileImageUrl = null,
        )
    }
}
