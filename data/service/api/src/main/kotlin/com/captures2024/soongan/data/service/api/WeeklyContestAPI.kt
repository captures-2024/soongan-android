package com.captures2024.soongan.data.service.api

import com.captures2024.soongan.core.model.AppConst
import com.captures2024.soongan.core.model.network.request.weekly.contests.EditPostRequest
import com.captures2024.soongan.core.model.network.response.BaseResponse
import com.captures2024.soongan.core.model.network.response.weekly.contests.EditPostResponse
import com.captures2024.soongan.core.model.network.response.weekly.contests.GetWeeklyContestInfoListResponse
import com.captures2024.soongan.core.model.network.response.weekly.contests.GetGalleryResponse
import com.captures2024.soongan.core.model.network.response.weekly.contests.GetMyGalleryResponse
import com.captures2024.soongan.core.model.network.response.weekly.contests.GetPostInfoResponse
import com.captures2024.soongan.core.model.network.response.weekly.contests.RegisterPostResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface WeeklyContestAPI {

    @GET("weekly/contests/posts")
    suspend fun getGalleryInfo(
        @Query("round") round: Int?,
        @Query("orderCriteria") orderType: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
    ): Response<BaseResponse<GetGalleryResponse>>

    @Headers(AppConst.Network.ACCESS_TOKEN_ALLOW)
    @Multipart
    @POST("weekly/contests/posts")
    suspend fun registerPost(
        @Part("title") title: String?,
        @Part imageFile: MultipartBody.Part?,
    ): Response<BaseResponse<RegisterPostResponse>>

    @GET("weekly/contests/posts/{postId}")
    suspend fun getPostInfoByGuest(
        @Path("postId") postId: Long,
    ): Response<BaseResponse<GetPostInfoResponse>>

    @Headers(AppConst.Network.ACCESS_TOKEN_ALLOW)
    @GET("weekly/contests/posts/{postId}")
    suspend fun getPostInfo(
        @Path("postId") postId: Long,
    ): Response<BaseResponse<GetPostInfoResponse>>

    @Headers(AppConst.Network.ACCESS_TOKEN_ALLOW)
    @DELETE("weekly/contests/posts/{postId}")
    suspend fun deletePost(
        @Path("postId") postId: Long,
    ): Response<BaseResponse<Unit>>

    @Headers(AppConst.Network.ACCESS_TOKEN_ALLOW)
    @PATCH("weekly/contests/posts/{postId}")
    suspend fun editPostTitle(
        @Path("postId") postId: Long,
        @Body request: EditPostRequest,
    ): Response<BaseResponse<EditPostResponse>>

    @GET("weekly/contests")
    suspend fun getContestInfoList(): Response<BaseResponse<GetWeeklyContestInfoListResponse>>

    @Headers(AppConst.Network.ACCESS_TOKEN_ALLOW)
    @GET("weekly/contests/posts/my-hisotry")
    suspend fun getMyGalleryInfo(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
    ): Response<BaseResponse<GetMyGalleryResponse>>
}
