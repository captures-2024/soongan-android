package com.captures2024.soongan.data.service.api

import com.captures2024.soongan.core.model.AppConst
import com.captures2024.soongan.core.model.network.request.notifications.PatchNotificationSettingsRequest
import com.captures2024.soongan.core.model.network.response.BaseResponse
import com.captures2024.soongan.core.model.network.response.notifications.GetNotificationSettingsResponse
import com.captures2024.soongan.core.model.network.response.notifications.GetNotificationsCountResponse
import com.captures2024.soongan.core.model.network.response.notifications.GetNotificationsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface NotificationsAPI {

    @Headers(AppConst.Network.ACCESS_TOKEN_ALLOW)
    @GET("notifications")
    suspend fun getNotifications(
        @Query("type") type: String,
    ): Response<BaseResponse<GetNotificationsResponse>>

    @Headers(AppConst.Network.ACCESS_TOKEN_ALLOW)
    @GET("notifications/unread-count")
    suspend fun getUnreadNotificationsCount(): Response<BaseResponse<GetNotificationsCountResponse>>

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

    @Headers(AppConst.Network.ACCESS_TOKEN_ALLOW)
    @GET("notifications/settings")
    suspend fun getNotificationSettings(): Response<BaseResponse<GetNotificationSettingsResponse>>

    @Headers(AppConst.Network.ACCESS_TOKEN_ALLOW)
    @PATCH("notifications/settings")
    suspend fun patchNotificationSettings(
        @Body request: PatchNotificationSettingsRequest,
    ): Response<BaseResponse<GetNotificationSettingsResponse>>
}
