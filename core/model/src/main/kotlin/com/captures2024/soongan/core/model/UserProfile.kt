package com.captures2024.soongan.core.model

data class UserProfile(
    val nickname: String = "user1",
    val selfIntroduction: String = "본인을 소개해주세요",
    val profileImageUrl: String? = null,
)