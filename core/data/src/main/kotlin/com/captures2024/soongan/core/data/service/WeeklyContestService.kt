package com.captures2024.soongan.core.data.service

import com.captures2024.soongan.core.model.network.response.BaseResponse
import com.captures2024.soongan.core.model.network.response.weekly.contests.GetGalleryResponse
import com.captures2024.soongan.core.model.network.response.weekly.contests.GetMyGalleryResponse
import com.captures2024.soongan.core.model.network.response.weekly.contests.GetPostInfoResponse
import com.captures2024.soongan.core.model.network.response.weekly.contests.RegisterPostResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface WeeklyContestService {

    @GET("weekly/contests/posts")
    suspend fun getGalleryInfo(
        @Query("round") round: Int?,
        @Query("orderCriteria") orderType: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
    ): Response<BaseResponse<GetGalleryResponse>>

    @Headers("Authorization: true")
    @Multipart
    @POST("weekly/contests/posts")
    suspend fun registerPost(
//        @Part("weeklyContestRound") weeklyContestRound: Int?,
        @Part("title") title: String?,
        @Part imageFile: MultipartBody.Part?,
    ): Response<BaseResponse<RegisterPostResponse>>

    @Headers("Authorization: true")
    @GET("weekly/contests/posts/{postId}")
    suspend fun getPostInfo(
        @Path("postId") postId: Long,
    ): Response<BaseResponse<GetPostInfoResponse>>

    @Headers("Authorization: true")
    @GET("weekly/contests/posts/my-hisotry")
    suspend fun getMyGalleryInfo(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
    ): Response<BaseResponse<GetMyGalleryResponse>>
}
