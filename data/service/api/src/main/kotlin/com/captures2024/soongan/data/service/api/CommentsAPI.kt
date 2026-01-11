package com.captures2024.soongan.data.service.api

import com.captures2024.soongan.core.model.network.request.comments.ModifyCommentRequest
import com.captures2024.soongan.core.model.network.request.comments.PostCommentRequest
import com.captures2024.soongan.core.model.network.response.BaseResponse
import com.captures2024.soongan.core.model.network.response.comments.GetCommentRepliesResponse
import com.captures2024.soongan.core.model.network.response.comments.GetCommentsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface CommentsAPI {

    @GET("comments")
    suspend fun getComments(
        @Query("contestType") contestType: String,
        @Query("postId") postId: Int,
    ): Response<BaseResponse<GetCommentsResponse>>

    @GET("comments")
    suspend fun getComments(
        @Query("contestType") contestType: String,
        @Query("postId") postId: Int,
        @Query("page") page: Int,
    ): Response<BaseResponse<GetCommentsResponse>>

    @GET("comments")
    suspend fun getComments(
        @Query("contestType") contestType: String,
        @Query("postId") postId: Int,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): Response<BaseResponse<GetCommentsResponse>>

    @PUT("comments")
    suspend fun modifyComment(
        @Body request: ModifyCommentRequest,
    ): Response<BaseResponse<Unit>>

    @POST("comments")
    suspend fun postComment(
        @Body request: PostCommentRequest,
    ): Response<BaseResponse<Unit>>

    // TODO 수정 필요
    @DELETE("comments")
    suspend fun deleteComment(): Response<BaseResponse<Unit>>

    @GET("comments/replies")
    suspend fun getCommentReplies(
        @Query("contestType") contestType: String,
        @Query("parentCommentId") parentCommentId: Int,
    ): Response<BaseResponse<GetCommentRepliesResponse>>

    @GET("comments/replies")
    suspend fun getCommentReplies(
        @Query("contestType") contestType: String,
        @Query("parentCommentId") parentCommentId: Int,
        @Query("page") page: Int,
    ): Response<BaseResponse<GetCommentRepliesResponse>>

    @GET("comments/replies")
    suspend fun getCommentReplies(
        @Query("contestType") contestType: String,
        @Query("parentCommentId") parentCommentId: Int,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): Response<BaseResponse<GetCommentRepliesResponse>>
}
