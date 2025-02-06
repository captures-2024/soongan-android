package com.captures2024.soongan.core.data.remote.impl

import com.captures2024.soongan.core.data.mapper.toReportInfoDto
import com.captures2024.soongan.core.data.remote.ReportDataSource
import com.captures2024.soongan.core.data.service.ReportService
import com.captures2024.soongan.core.data.utils.safeAPICall
import com.captures2024.soongan.core.model.dto.ReportInfoDto
import com.captures2024.soongan.core.model.network.request.report.PostReportRequest
import com.captures2024.soongan.core.model.utils.ReportTargetType
import com.captures2024.soongan.core.model.utils.ReportType
import javax.inject.Inject

class ReportDataSourceImpl
@Inject
constructor(
    private val service: ReportService,
) : ReportDataSource {

    override suspend fun postReport(
        targetId: Long,
        targetType: ReportTargetType,
        reportType: ReportType,
        reason: String?,
    ): ReportInfoDto? = safeAPICall {
        service.postReport(
            request = PostReportRequest(
                targetId = targetId,
                targetType = targetType.name,
                reportType = reportType.name,
                reason = reason
            )
        )
    }.body?.responseData?.toReportInfoDto()
}