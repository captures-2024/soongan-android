package com.captures2024.soongan.core.domain.usecase.members

import com.captures2024.soongan.core.data.repository.MembersRepository
import com.captures2024.soongan.core.domain.runSuspendCatching
import javax.inject.Inject

class PatchBirthYearUseCase
@Inject
constructor(
    private val membersRepository: MembersRepository,
) {

    suspend operator fun invoke(
        birthYear: Int,
    ): Result<Boolean> = runSuspendCatching {
        val userInfoDto = membersRepository.patchBirthYear(
            birthYear = birthYear,
        )

        return@runSuspendCatching birthYear == userInfoDto.birthYear
    }
}
