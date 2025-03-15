package com.captures2024.soongan.core.data.service

import com.captures2024.soongan.core.model.network.request.like.PostLikeRequest
import com.captures2024.soongan.core.model.network.response.BaseResponse
import com.captures2024.soongan.core.model.network.response.like.PostLikeResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.HTTP
import retrofit2.http.Headers
import retrofit2.http.PUT

interface PostLikeService {
    @Headers("Authorization: true")
    @PUT("posts/like")
    suspend fun putPostLike(
        @Body request: PostLikeRequest,
    ): Response<BaseResponse<PostLikeResponse>>

    @Headers("Authorization: true")
    @HTTP(method = "DELETE", path = "posts/like", hasBody = true)
    suspend fun deletePostLike(
        @Body request: PostLikeRequest,
    ): Response<BaseResponse<PostLikeResponse>>
}