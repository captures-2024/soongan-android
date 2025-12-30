package com.captures2024.soongan.domain.repository.report

import com.captures2024.soongan.core.model.dto.ReportInfoDto
import com.captures2024.soongan.core.model.utils.ReportTargetType
import com.captures2024.soongan.core.model.utils.ReportType

interface ReportRepository {

    suspend fun postReport(
        targetId: Long,
        targetType: ReportTargetType,
        reportType: ReportType,
        reason: String?,
    ): ReportInfoDto

    suspend fun postExplain(
        targetId: Long,
        explain: String,
    ): Boolean
}
