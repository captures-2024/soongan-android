package com.captures2024.soongan.core.data.service

import com.captures2024.soongan.core.model.network.request.members.PatchProfileRequest
import com.captures2024.soongan.core.model.network.response.BaseResponse
import com.captures2024.soongan.core.model.network.response.members.GetMemberInfoResponse
import com.captures2024.soongan.core.model.network.response.members.PatchBirthYearResponse
import com.captures2024.soongan.core.model.network.response.members.PatchProfileResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.Query

interface MembersService {

    @Headers("Authorization: true")
    @PATCH("members/profile")
    suspend fun patchProfile(
        @Body request: PatchProfileRequest
    ): Response<BaseResponse<PatchProfileResponse>>

    @Headers("Authorization: true")
    @PATCH("members/birth-year")
    suspend fun patchBirthYear(
        @Query("birthYear") birthYear: Int,
    ): Response<BaseResponse<PatchBirthYearResponse>>

    @Headers("Authorization: true")
    @GET("members")
    suspend fun getMemberInfo(): Response<BaseResponse<GetMemberInfoResponse>>

    @Headers("Authorization: true")
    @GET("members/check-nickname")
    suspend fun isVerifiedNickname(
        @Query("nickname") nickname: String,
    ): Response<BaseResponse<Boolean>>
}