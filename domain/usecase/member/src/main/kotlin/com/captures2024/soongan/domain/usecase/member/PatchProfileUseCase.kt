package com.captures2024.soongan.domain.usecase.member

interface PatchProfileUseCase {

    suspend operator fun invoke(
        nickname: String? = null,
        selfIntroduction: String? = null,
        profileImageUrl: String? = null,
        isDefaultProfileImage: Boolean = false,
    ): Result<Boolean>
}
