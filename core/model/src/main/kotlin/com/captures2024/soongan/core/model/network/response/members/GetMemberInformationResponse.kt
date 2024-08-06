package com.captures2024.soongan.core.model.network.response.members

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetMemberInformationResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("email")
    val email: String,
    @SerialName("nickname")
    val nickname: String = "",
    @SerialName("birthDate")
    val birth: String = "",
    @SerialName("profileImageUrl")
    val profileImage: String,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("updatedAt")
    val updatedAt: String,
    @SerialName("withdrawalAt")
    val withdrawalAt: String,
    @SerialName("isEnabled")
    val isEnabled: Boolean,
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String,
    @SerialName("isAccountNonExpired")
    val isAccountNonExpired: Boolean,
    @SerialName("isAccountNonLocked")
    val isAccountNonLocked: Boolean,
    @SerialName("isCredentialsNonExpired")
    val isCredentialsNonExpired: Boolean,
)
