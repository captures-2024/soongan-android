package com.captures2024.soongan.data.service.api

import com.captures2024.soongan.core.model.AppConst
import com.captures2024.soongan.core.model.network.response.BaseResponse
import com.captures2024.soongan.core.model.network.response.notifications.GetNotificationsCountResponse
import com.captures2024.soongan.core.model.network.response.notifications.GetNotificationsResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface NotificationsService {

    @Headers(AppConst.Network.ACCESS_TOKEN_ALLOW)
    @GET("notifications")
    suspend fun getNotifications(
        @Query("type") type: String,
    ): Response<BaseResponse<GetNotificationsResponse>>

    @Headers(AppConst.Network.ACCESS_TOKEN_ALLOW)
    @GET("notifications/unread-count")
    suspend fun getNotificationsCount(): Response<BaseResponse<GetNotificationsCountResponse>>

    @Headers(AppConst.Network.ACCESS_TOKEN_ALLOW)
    @POST("notifications/{notificationId}/read")
    suspend fun postNotificationRead(
        @Path("notificationId") notificationId: Long,
    ): Response<BaseResponse<Unit>>

    @Headers(AppConst.Network.ACCESS_TOKEN_ALLOW)
    @DELETE("notifications/{notificationId}")
    suspend fun deleteNotification(
        @Path("notificationId") notificationId: Long,
    ): Response<BaseResponse<Unit>>
}