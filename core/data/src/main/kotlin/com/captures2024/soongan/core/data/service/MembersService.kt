package com.captures2024.soongan.core.data.service

import com.captures2024.soongan.core.model.network.response.BaseResponse
import com.captures2024.soongan.core.model.network.response.members.GetMemberInfoResponse
import com.captures2024.soongan.core.model.network.response.members.PatchBirthYearResponse
import com.captures2024.soongan.core.model.network.response.members.PatchProfileResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.Part
import retrofit2.http.Query

interface MembersService {

    @Headers("Authorization: true")
    @Multipart
    @PATCH("members/profile")
    suspend fun patchProfile(
        @Part("nickname") nickname: RequestBody?,
        @Part("selfIntroduction") selfIntroduction: RequestBody?,
        @Part profileImage: MultipartBody.Part?,
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