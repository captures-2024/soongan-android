package com.captures2024.soongan.core.domain.usecase.members

import com.captures2024.soongan.core.domain.repository.MembersRepository
import com.captures2024.soongan.core.domain.runSuspendCatching
import com.captures2024.soongan.core.model.network.SocialSignType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SigningGoogleUseCase
@Inject
constructor(
    private val repository: MembersRepository
) {

    suspend operator fun invoke(
        token: String,
        fcmToken: String
    ): Result<Boolean> = runSuspendCatching {
        repository.signingSocialPlatform(
            type = SocialSignType.GOOGLE,
            token = token,
            fcmToken = fcmToken
        ).result
    }

}