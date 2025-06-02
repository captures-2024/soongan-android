package com.captures2024.soongan.domain.usecase.member.impl

import com.captures2024.soongan.data.repository.member.MemberRepository
import com.captures2024.soongan.domain.usecase.member.PatchBirthYearUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class PatchBirthYearUseCaseImpl
@Inject
constructor(
    private val repository: MemberRepository,
) : PatchBirthYearUseCase {

    override suspend fun invoke(birthYear: Int): Result<Boolean> = runSuspendCatching {
        val userInfoDto = repository.patchBirthYear(
            birthYear = birthYear,
        )

        return@runSuspendCatching birthYear == userInfoDto.birthYear
    }
}
