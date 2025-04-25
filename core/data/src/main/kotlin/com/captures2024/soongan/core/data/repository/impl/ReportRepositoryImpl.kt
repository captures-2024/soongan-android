package com.captures2024.soongan.core.data.repository.impl

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.data.source.report.remote.ReportRemoteDataSource
import com.captures2024.soongan.core.data.repository.ReportRepository
import com.captures2024.soongan.core.model.dto.ReportInfoDto
import com.captures2024.soongan.core.model.utils.ReportTargetType
import com.captures2024.soongan.core.model.utils.ReportType
import javax.inject.Inject

class ReportRepositoryImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val dataSource: ReportRemoteDataSource,
) : ReportRepository {

    init {
        analyticsHelper.d { "ReportRepository::init" }
    }

    override suspend fun postReport(
        targetId: Long,
        targetType: ReportTargetType,
        reportType: ReportType,
        reason: String?,
    ): ReportInfoDto {
        val reportInfo = dataSource.postReport(
            targetId = targetId,
            targetType = targetType,
            reportType = reportType,
            reason = reason,
        )

        if (reportInfo == null) {
            throw NullPointerException("reportInfo is null")
        }

        if (targetId != reportInfo.targetId ||
            targetType.name != reportInfo.targetType ||
            reportType.name != reportInfo.reportType ||
            reason != reportInfo.reason) {
            error("mismatch between request and response data")
        }

        return reportInfo
    }
}
