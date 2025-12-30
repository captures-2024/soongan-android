package com.captures2024.soongan.domain.usecase.auth.impl

import com.captures2024.soongan.core.model.utils.SocialSignType
import com.captures2024.soongan.domain.repository.auth.AuthRepository
import com.captures2024.soongan.domain.usecase.auth.SigningGoogleUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class SigningGoogleUseCaseImpl
@Inject
constructor(
    private val repository: AuthRepository,
) : SigningGoogleUseCase {

    override suspend operator fun invoke(
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
