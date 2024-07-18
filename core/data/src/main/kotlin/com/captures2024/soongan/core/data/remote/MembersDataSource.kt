package com.captures2024.soongan.core.data.remote

import com.captures2024.soongan.core.model.network.SocialSignType
import com.captures2024.soongan.core.model.network.response.SignWithTokenResponse

interface MembersDataSource {

    suspend fun signWithToken(
        type: SocialSignType,
        token: String
    ): SignWithTokenResponse?

}
