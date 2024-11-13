package com.captures2024.soongan.core.domain.usecase.auth

import com.captures2024.soongan.core.data.repository.AuthRepository
import com.captures2024.soongan.core.domain.runSuspendCatching
import com.captures2024.soongan.core.model.network.SocialSignType
import javax.inject.Inject

class SigningGoogleUseCase
@Inject
constructor(
    private val repository: AuthRepository,
) {

    suspend operator fun invoke(
        token: String,
        fcmToken: String,
    ): Result<Boolean> = runSuspendCatching {
        repository.signingSocialPlatform(
            type = SocialSignType.GOOGLE,
            token = token,
            fcmToken = fcmToken,
        ).result
    }
}
