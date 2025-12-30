package com.captures2024.soongan.data.repository.report.impl

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.model.dto.ReportInfoDto
import com.captures2024.soongan.core.model.utils.ReportTargetType
import com.captures2024.soongan.core.model.utils.ReportType
import com.captures2024.soongan.data.source.contest.local.ContentVisibilityLocalDataSource
import com.captures2024.soongan.data.source.report.remote.ReportRemoteDataSource
import com.captures2024.soongan.domain.repository.report.ReportRepository
import javax.inject.Inject

class ReportRepositoryImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val reportRemoteDataSource: ReportRemoteDataSource,
    private val contentVisibilityLocalDataSource: ContentVisibilityLocalDataSource,
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
        val reportInfo = reportRemoteDataSource.postReport(
            targetId = targetId,
            targetType = targetType,
            reportType = reportType,
            reason = reason,
        )

        if (reportInfo == null) {
            throw NullPointerException("reportInfo is null")
        }

        val condition = targetId != reportInfo.targetId ||
            targetType.name != reportInfo.targetType ||
            reportType.name != reportInfo.reportType ||
            reason != reportInfo.reason

        if (condition) {
            error("mismatch between request and response data")
        }

        contentVisibilityLocalDataSource.emitHidePostEvent(postId = targetId)

        return reportInfo
    }

    override suspend fun postExplain(
        targetId: Long,
        explain: String,
    ): Boolean {
        val result = reportRemoteDataSource.postExplain(
            targetId = targetId,
            targetType = ReportTargetType.WEEKLY_POST,
            explain = explain,
        )

        return result
    }
}
