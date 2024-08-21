package com.captures2024.soongan.core.domain.usecase.members

import com.captures2024.soongan.core.domain.repository.MembersRepository
import com.captures2024.soongan.core.domain.runSuspendCatching
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IsAllowUserInfoUseCase
@Inject
constructor(
    private val repository: MembersRepository
) {

    suspend operator fun invoke(): Result<Boolean> = runSuspendCatching {
        val userInfo = repository.getMemberInformation()

        if (userInfo.user.nickname.isEmpty()) {
            return@runSuspendCatching false
        }

        if (userInfo.user.birthDate.isEmpty()) {
            return@runSuspendCatching false
        }

        return@runSuspendCatching true
    }

}