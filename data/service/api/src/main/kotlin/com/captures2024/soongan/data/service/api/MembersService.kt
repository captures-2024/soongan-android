package com.captures2024.soongan.data.service.api

import com.captures2024.soongan.core.model.AppConst
import com.captures2024.soongan.core.model.network.response.BaseResponse
import com.captures2024.soongan.core.model.network.response.members.GetMemberInfoResponse
import com.captures2024.soongan.core.model.network.response.members.PatchBirthYearResponse
import com.captures2024.soongan.core.model.network.response.members.PatchProfileResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.Part
import retrofit2.http.Query

interface MembersService {

    @Headers(AppConst.Network.ACCESS_TOKEN_ALLOW)
    @Multipart
    @PATCH("members/profile")
    suspend fun patchProfile(
        @Part("nickname") nickname: String?,
        @Part("selfIntroduction") selfIntroduction: String?,
        @Part profileImageUrl: MultipartBody.Part?,
        @Part("isDefaultProfileImage") isDefaultProfileImage: Boolean,
    ): Response<BaseResponse<PatchProfileResponse>>

    @Headers(AppConst.Network.ACCESS_TOKEN_ALLOW)
    @PATCH("members/birth-year")
    suspend fun patchBirthYear(
        @Query("birthYear") birthYear: Int,
    ): Response<BaseResponse<PatchBirthYearResponse>>

    @Headers(AppConst.Network.ACCESS_TOKEN_ALLOW)
    @GET("members")
    suspend fun getMemberInfo(): Response<BaseResponse<GetMemberInfoResponse>>

    @GET("members/check-nickname")
    suspend fun isVerifiedNickname(
        @Query("nickname") nickname: String,
    ): Response<BaseResponse<Boolean>>
}