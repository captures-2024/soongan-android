package com.captures2024.soongan.core.data.remote

import com.captures2024.soongan.core.model.network.SocialSignType
import com.captures2024.soongan.core.model.network.response.members.GetMemberInformationResponse
import com.captures2024.soongan.core.model.network.response.members.RegisterNicknameResponse
import com.captures2024.soongan.core.model.network.response.members.ReissueTokenResponse
import com.captures2024.soongan.core.model.network.response.members.SignInWithTokenResponse

interface MembersDataSource {

    suspend fun withdrawWithToken(): Boolean

    suspend fun signOutWithToken(): Boolean

    suspend fun signInWithToken(
        type: SocialSignType,
        token: String
    ): SignInWithTokenResponse?

    suspend fun reissueToken(
        accessToken: String,
        refreshToken: String
    ): ReissueTokenResponse?

    suspend fun registerProfileImage()

    suspend fun registerNickname(
        nickname: String
    ): RegisterNicknameResponse?

    suspend fun getMemberInformation(): GetMemberInformationResponse?

    suspend fun isDuplicateNickname(
        nickname: String
    ): Boolean?

}
