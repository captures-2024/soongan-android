package com.captures2024.soongan.core.data.repository.impl

import com.captures2024.soongan.core.data.remote.ReportDataSource
import com.captures2024.soongan.core.data.repository.ReportRepository
import com.captures2024.soongan.core.model.dto.ResultConditionDto
import com.captures2024.soongan.core.model.utils.ReportTargetType
import com.captures2024.soongan.core.model.utils.ReportType
import javax.inject.Inject

class ReportRepositoryImpl
@Inject
constructor(
    private val dataSource: ReportDataSource,
) : ReportRepository {

    override suspend fun postReport(
        targetId: Long,
        targetType: ReportTargetType,
        reportType: ReportType,
        reason: String?,
    ): ResultConditionDto {

        val reportInfo = dataSource.postReport(
            targetId = targetId,
            targetType = targetType,
            reportType = reportType,
            reason = reason
        )

        if (reportInfo == null) {
            throw NullPointerException("reportInfo is null")
        }

        if (
            targetId != reportInfo.targetId
            || targetType.name != reportInfo.targetType
            || reportType.name != reportInfo.reportType
            || reason != reportInfo.reason
        ) {
            return ResultConditionDto(result = false)
        }

        return ResultConditionDto(result = true)
    }
}