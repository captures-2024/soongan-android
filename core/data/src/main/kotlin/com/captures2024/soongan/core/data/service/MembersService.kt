package com.captures2024.soongan.core.data.service

import com.captures2024.soongan.core.model.network.request.members.ReissueTokenRequest
import com.captures2024.soongan.core.model.network.request.members.SignWithTokenRequest
import com.captures2024.soongan.core.model.network.response.members.GetMemberInformationResponse
import com.captures2024.soongan.core.model.network.response.members.RegisterNicknameResponse
import com.captures2024.soongan.core.model.network.response.members.ReissueTokenResponse
import com.captures2024.soongan.core.model.network.response.members.SignInWithTokenResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.Query

interface MembersService {

    /**
     * 회원 탈퇴 API
     *
     * 회원을 탈퇴합니다.
     **/
    @POST("members/withdraw")
    suspend fun withdrawWithToken(): Response<Unit>

    /**
     * 로그아웃 API
     *
     * 로그인시 발급한 JWT를 말소합니다.
     **/
    @POST("members/logout")
    suspend fun signOutWithToken(): Response<Unit>

    /**
     * 로그인 API
     *
     * idToken을 이용하여 로그인을 수행하고, JWT를 발급합니다.
     **/
    @POST("members/login")
    suspend fun signInWithToken(
        @Body request: SignWithTokenRequest
    ): Response<SignInWithTokenResponse>

    /**
     * JWT 갱신 API
     *
     * Refresh Token을 이용하여 JWT를 갱신합니다.
     **/
    @PATCH("members/refresh")
    suspend fun reissueToken(
        @Body request: ReissueTokenRequest
    ): Response<ReissueTokenResponse>

    /**
     * 프로필 사진 변경 API
     *
     * 프로필 사진을 변경합니다.
     **/
    @PATCH("members/profile-image")
    suspend fun registerProfileImage(
        /* TODO */
    )

    /**
     * 닉네임 변경 API
     *
     * 닉네임을 변경합니다.
     **/
    @PATCH("members/nickname")
    suspend fun registerNickname(
        @Query("newNickname") nickname: String
    ): Response<RegisterNicknameResponse>

    /**
     * 회원 정보 조회 API
     *
     * JWT를 읽어 로그인한 회원의 정보를 조회합니다.
     **/
    @Headers("Authorization: true")
    @GET("members")
    suspend fun getMemberInformation(): Response<GetMemberInformationResponse>

    /**
     * 닉네임 중복 확인 API
     *
     * 닉네임이 중복되는지 확인합니다.
     * @return true면 사용 가능, false면 중복.
     **/
    @GET("members/check-nickname")
    suspend fun isDuplicateNickname(
        @Query("nickname") nickname: String
    ): Response<Boolean>

}