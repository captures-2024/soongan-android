package com.captures2024.soongan.core.domain.usecase.members

import com.captures2024.soongan.core.data.repository.MembersRepository
import com.captures2024.soongan.core.domain.runSuspendCatching
import javax.inject.Inject

class PatchProfileUseCase
@Inject
constructor(
    private val membersRepository: MembersRepository,
) {

    suspend operator fun invoke(
        nickname: String? = null,
        selfIntroduction: String? = null,
        profileImageUrl: String? = null,
        isDefaultProfileImage: Boolean = false,
    ): Result<Boolean> = runSuspendCatching {
        val userInfoDto = membersRepository.patchProfile(
            nickname = nickname,
            selfIntroduction = selfIntroduction,
            profileImageUrl = profileImageUrl,
            isDefaultProfileImage = isDefaultProfileImage,
        )

        return@runSuspendCatching (nickname == userInfoDto.nickname && selfIntroduction == userInfoDto.selfIntroduction && profileImageUrl == userInfoDto.profileImageUrl)
    }
}