package com.captures2024.soongan.core.domain.usecase.members

import com.captures2024.soongan.core.data.repository.MembersRepository
import com.captures2024.soongan.core.model.dto.UserInfoDto
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetCurrentMemberFlow
@Inject
constructor(
    private val repository: MembersRepository,
) {

    operator fun invoke(): StateFlow<UserInfoDto?> = repository.currentMember
}