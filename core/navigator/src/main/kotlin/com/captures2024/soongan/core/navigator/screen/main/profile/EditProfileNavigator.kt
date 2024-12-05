package com.captures2024.soongan.core.navigator.screen.main.profile

import kotlinx.serialization.Serializable

@Serializable
data class EditProfileNavigator(
    val image: String?,
    val nickname: String,
    val selfIntroduction: String,
)