package com.captures2024.soongan.core.data.remote.impl

import com.captures2024.soongan.core.data.remote.MembersDataSource
import com.captures2024.soongan.core.data.service.MembersService
import com.captures2024.soongan.core.data.utils.safeAPICall
import com.captures2024.soongan.core.model.network.SocialSignType
import com.captures2024.soongan.core.model.network.request.members.ReissueTokenRequest
import com.captures2024.soongan.core.model.network.request.members.SignWithTokenRequest
import com.captures2024.soongan.core.model.network.response.members.GetMemberInformationResponse
import com.captures2024.soongan.core.model.network.response.members.RegisterNicknameResponse
import com.captures2024.soongan.core.model.network.response.members.ReissueTokenResponse
import com.captures2024.soongan.core.model.network.response.members.SignInWithTokenResponse
import javax.inject.Inject

class MembersDataSourceImpl
@Inject
constructor(
    private val service: MembersService
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
        token: String
    ): SignInWithTokenResponse? = safeAPICall {
        service.signInWithToken(
            request = SignWithTokenRequest(
                provider = type.provider,
                idToken = token
            )
        )
    }.body

    override suspend fun reissueToken(
        accessToken: String,
        refreshToken: String
    ): ReissueTokenResponse? = safeAPICall {
        service.reissueToken(
            request = ReissueTokenRequest(
                accessToken = accessToken,
                refreshToken = refreshToken
            )
        )
    }.body

    override suspend fun registerProfileImage() {
        TODO("Not yet implemented")
    }

    override suspend fun registerNickname(
        nickname: String
    ): RegisterNicknameResponse? = safeAPICall {
        service.registerNickname(
            nickname = nickname
        )
    }.body

    override suspend fun getMemberInformation(): GetMemberInformationResponse? = safeAPICall {
        service.getMemberInformation()
    }.body

    override suspend fun isDuplicateNickname(
        nickname: String
    ): Boolean {
        val result = safeAPICall {
            service.isDuplicateNickname(
                nickname = nickname
            )
        }

        return when (result.body) {
            null -> false
            else -> result.body
        }
    }

}
