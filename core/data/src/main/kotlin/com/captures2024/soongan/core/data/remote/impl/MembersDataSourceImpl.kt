package com.captures2024.soongan.core.data.remote.impl

import com.captures2024.soongan.core.data.remote.MembersDataSource
import com.captures2024.soongan.core.data.service.MembersService
import com.captures2024.soongan.core.data.utils.safeAPICall
import com.captures2024.soongan.core.model.SocialSignType
import com.captures2024.soongan.core.model.request.SignWithTokenRequest
import com.captures2024.soongan.core.model.response.SignWithTokenResponse
import javax.inject.Inject

class MembersDataSourceImpl
@Inject
constructor(
    private val service: MembersService
) : MembersDataSource {

    override suspend fun signWithToken(
        type: SocialSignType,
        token: String
    ): SignWithTokenResponse? = safeAPICall {
        service.signWithToken(
            request = SignWithTokenRequest(
                provider = type.provider,
                idToken = token
            )
        )
    }.body

}
