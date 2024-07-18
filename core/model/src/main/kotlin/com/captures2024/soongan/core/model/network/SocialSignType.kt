package com.captures2024.soongan.core.model.network

enum class SocialSignType(
    val provider: String
) {
    APPLE(provider = "APPLE"),
    GOOGLE(provider = "GOOGLE"),
    KAKAO(provider = "KAKAO")
}