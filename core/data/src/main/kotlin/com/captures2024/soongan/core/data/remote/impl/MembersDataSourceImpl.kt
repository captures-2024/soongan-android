package com.captures2024.soongan.core.data.remote.impl

import com.captures2024.soongan.core.data.mapper.toUserInfoDto
import com.captures2024.soongan.core.data.remote.MembersDataSource
import com.captures2024.soongan.core.data.service.MembersService
import com.captures2024.soongan.core.data.utils.safeAPICall
import com.captures2024.soongan.core.model.dto.UserDto
import com.captures2024.soongan.core.model.dto.UserInfoDto
import com.captures2024.soongan.core.model.network.SocialSignType
import com.captures2024.soongan.core.model.network.request.members.ReissueTokenRequest
import com.captures2024.soongan.core.model.network.request.members.SignWithTokenRequest
import com.captures2024.soongan.core.model.network.response.members.ReissueTokenResponse
import com.captures2024.soongan.core.model.network.response.members.SignInWithTokenResponse
import javax.inject.Inject

class MembersDataSourceImpl
@Inject
constructor(
    private val service: MembersService,
) : MembersDataSource {
    override suspend fun withdrawWithToken(): Boolean {
        val result = safeAPICall { service.withdrawWithToken() }

        return when (result.body) {
            null -> false
            else -> true
        }
    }

    override suspend fun signOutWithToken(): Boolean {
        val result = safeAPICall { service.signOutWithToken() }

        return when (result.body) {
            null -> false
            else -> true
        }
    }

    override suspend fun signInWithToken(
        type: SocialSignType,
        token: String,
        fcmToken: String,
    ): SignInWithTokenResponse? = safeAPICall {
        service.signInWithToken(
            request = SignWithTokenRequest(
                provider = type.provider,
                idToken = token,
                fcmToken = fcmToken,
            ),
        )
    }.body?.responseData

    override suspend fun reissueToken(
        accessToken: String,
        refreshToken: String,
    ): ReissueTokenResponse? = safeAPICall {
        service.reissueToken(
            request = ReissueTokenRequest(
                accessToken = accessToken,
                refreshToken = refreshToken,
            ),
        )
    }.body?.responseData

    override suspend fun registerProfileImage() {
        TODO("Not yet implemented")
    }

    override suspend fun registerNickname(
        nickname: String,
    ): UserDto? {
        val result = safeAPICall {
            service.registerNickname(
                nickname = nickname,
            )
        }.body?.responseData

        return when (result) {
            null -> null
            else -> UserDto(
                email = result.id,
                nickname = result.nickname,
                birthDate = "",
            )
        }
    }

    override suspend fun getMemberInformation(): UserInfoDto? = safeAPICall {
        service.getMemberInformation()
    }.body?.responseData?.toUserInfoDto()

    override suspend fun isDuplicateNickname(
        nickname: String,
    ): Boolean {
        val result = safeAPICall {
            service.isDuplicateNickname(
                nickname = nickname,
            )
        }

        return when (val data = result.body?.responseData) {
            null -> false
            else -> data
        }
    }
}
