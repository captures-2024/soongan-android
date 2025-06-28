package com.captures2024.soongan.data.source.report.impl.remote

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.model.dto.ReportInfoDto
import com.captures2024.soongan.core.model.network.request.report.PostReportRequest
import com.captures2024.soongan.core.model.utils.ReportTargetType
import com.captures2024.soongan.core.model.utils.ReportType
import com.captures2024.soongan.data.source.report.impl.mapper.toReportInfoDto
import com.captures2024.soongan.data.service.api.ReportAPI
import com.captures2024.soongan.data.source.report.remote.ReportRemoteDataSource
import com.captures2024.soongan.data.service.api.utils.safeAPICall
import javax.inject.Inject

class ReportRemoteDataSourceImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val reportAPI: ReportAPI,
) : ReportRemoteDataSource {

    override suspend fun postReport(
        targetId: Long,
        targetType: ReportTargetType,
        reportType: ReportType,
        reason: String?,
    ): ReportInfoDto? {
        analyticsHelper.d { "postReport - targetId: $targetId, targetType: $targetType, reportType: $reportType, reason: $reason" }

        val response = safeAPICall {
            reportAPI.postReport(
                request = PostReportRequest(
                    targetId = targetId,
                    targetType = targetType.name,
                    reportType = reportType.name,
                    reason = reason,
                ),
            )
        }

        val responseHeader = response.headers

        analyticsHelper.d { "postReport - responseHeader: $responseHeader" }

        val responseBody = response.body

        analyticsHelper.d { "postReport - responseBody: $responseBody" }

        return responseBody?.responseData?.toReportInfoDto()
    }
}
