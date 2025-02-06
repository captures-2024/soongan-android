package com.captures2024.soongan.core.data.remote

import com.captures2024.soongan.core.model.dto.ReportInfoDto
import com.captures2024.soongan.core.model.utils.ReportTargetType
import com.captures2024.soongan.core.model.utils.ReportType

interface ReportDataSource {

    suspend fun postReport(
        targetId: Long,
        targetType : ReportTargetType,
        reportType: ReportType,
        reason: String?
    ): ReportInfoDto?
}