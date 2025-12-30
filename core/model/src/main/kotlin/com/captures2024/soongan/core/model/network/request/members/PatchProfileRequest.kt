package com.captures2024.soongan.core.model.network.request.members

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PatchProfileRequest(
    @SerialName("nickname")
    val nickname: String? = null,
    @SerialName("selfIntroduction")
    val selfIntroduction: String? = null,
    @SerialName("profileImage")
    val profileImage: String? = null,
)
