package com.captures2024.soongan.domain.usecase.auth.impl

import com.captures2024.soongan.core.model.utils.SocialSignType
import com.captures2024.soongan.domain.repository.auth.AuthRepository
import com.captures2024.soongan.domain.usecase.auth.SigningKakaoUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class SigningKakaoUseCaseImpl
@Inject
constructor(
    private val repository: AuthRepository,
) : SigningKakaoUseCase {

    override suspend operator fun invoke(
        token: String,
        fcmToken: String,
    ): Result<Boolean> = runSuspendCatching {
        repository.signingSocialPlatform(
            type = SocialSignType.KAKAO,
            token = token,
            fcmToken = fcmToken,
        ).result
    }
}
