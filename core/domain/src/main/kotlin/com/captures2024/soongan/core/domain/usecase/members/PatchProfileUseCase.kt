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
        profileImage: String? = null,
    ): Result<Boolean> = runSuspendCatching {
        val userInfoDto = membersRepository.patchProfile(
            nickname = nickname,
            selfIntroduction = selfIntroduction,
            profileImage = profileImage,
        )

        return@runSuspendCatching (nickname == userInfoDto.nickname && selfIntroduction == userInfoDto.selfIntroduction && profileImage == userInfoDto.profileImageUrl)
    }
}