package com.captures2024.soongan.domain.usecase.report

import com.captures2024.soongan.core.model.utils.ReportTargetType
import com.captures2024.soongan.core.model.utils.ReportType

interface PostReportUseCase {

    suspend operator fun invoke(
        targetId: Long,
        targetType: ReportTargetType,
        reportType: ReportType,
        reason: String? = null,
    ): Result<Boolean>
}
