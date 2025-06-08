package com.captures2024.soongan.domain.usecase.member.impl

import com.captures2024.soongan.data.repository.member.MemberRepository
import com.captures2024.soongan.domain.usecase.member.PatchProfileUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class PatchProfileUseCaseImpl
@Inject
constructor(
    private val repository: MemberRepository,
) : PatchProfileUseCase {

    override suspend fun invoke(
        nickname: String?,
        selfIntroduction: String?,
        profileImageUrl: String?,
        isDefaultProfileImage: Boolean,
    ): Result<Boolean> = runSuspendCatching {
        val userInfoDto = repository.patchProfile(
            nickname = nickname,
            selfIntroduction = selfIntroduction,
            profileImageUrl = profileImageUrl,
            isDefaultProfileImage = isDefaultProfileImage,
        )

        return@runSuspendCatching (nickname == userInfoDto.nickname && selfIntroduction == userInfoDto.selfIntroduction && profileImageUrl == userInfoDto.profileImageUrl)
    }
}
