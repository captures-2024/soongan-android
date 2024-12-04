package com.captures2024.soongan.core.domain.usecase.members

import com.captures2024.soongan.core.data.repository.MembersRepository
import com.captures2024.soongan.core.domain.runSuspendCatching
import com.captures2024.soongan.core.model.dto.UserInfoDto
import javax.inject.Inject

class GetMemberInformationUseCase
@Inject
constructor(
    private val repository: MembersRepository,
) {

    suspend operator fun invoke(): Result<UserInfoDto> = runSuspendCatching {
        repository.getMemberInformation()
    }
}
