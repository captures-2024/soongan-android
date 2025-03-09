package com.captures2024.soongan.core.domain.usecase.report

import com.captures2024.soongan.core.data.repository.ReportRepository
import com.captures2024.soongan.core.domain.runSuspendCatching
import com.captures2024.soongan.core.model.utils.ReportTargetType
import com.captures2024.soongan.core.model.utils.ReportType
import javax.inject.Inject

class PostReportUseCase
@Inject
constructor(
    private val repository: ReportRepository,
) {

    suspend operator fun invoke(params: Params): Result<Boolean> = runSuspendCatching {
        val resultConditionDto = repository.postReport(
            targetId = params.targetId,
            targetType = params.targetType,
            reportType = params.reportType,
            reason = params.reason,
        )

        return@runSuspendCatching resultConditionDto.result
    }

    data class Params(
        val targetId: Long,
        val targetType: ReportTargetType,
        val reportType: ReportType,
        val reason: String? = null,
    )
}
