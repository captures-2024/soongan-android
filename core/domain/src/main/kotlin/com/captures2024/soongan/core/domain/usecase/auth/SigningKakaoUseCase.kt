package com.captures2024.soongan.core.domain.usecase.auth

import com.captures2024.soongan.core.data.repository.AuthRepository
import com.captures2024.soongan.core.domain.runSuspendCatching
import com.captures2024.soongan.core.model.utils.SocialSignType
import javax.inject.Inject

class SigningKakaoUseCase
@Inject
constructor(
    private val repository: AuthRepository,
) {

    suspend operator fun invoke(
        token: String,
        fcmToken: String
    ): Result<Boolean> = runSuspendCatching {
        repository.signingSocialPlatform(
            type = SocialSignType.KAKAO,
            token = token,
            fcmToken = fcmToken,
        ).result
    }
}
