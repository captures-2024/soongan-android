package com.captures2024.soongan.core.domain.usecase.members

import com.captures2024.soongan.core.data.repository.MembersRepository
import com.captures2024.soongan.core.domain.runSuspendCatching
import javax.inject.Inject

class RegisterNicknameUseCase
@Inject
constructor(
    private val repository: MembersRepository,
) {

    suspend operator fun invoke(nickname: String) = runSuspendCatching {
        repository.registerNickname(nickname)
    }
}
