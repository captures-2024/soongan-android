package com.captures2024.soongan.data.source.report.impl.service

import com.captures2024.soongan.core.model.AppConst
import com.captures2024.soongan.core.model.network.request.report.PostReportRequest
import com.captures2024.soongan.core.model.network.response.BaseResponse
import com.captures2024.soongan.core.model.network.response.report.PostReportResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ReportService {

    @Headers(AppConst.Network.ACCESS_TOKEN_ALLOW)
    @POST("report")
    suspend fun postReport(
        @Body request: PostReportRequest,
    ): Response<BaseResponse<PostReportResponse>>
}
