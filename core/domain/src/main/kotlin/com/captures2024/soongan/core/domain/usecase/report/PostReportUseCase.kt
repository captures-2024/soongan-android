package com.captures2024.soongan.core.domain.usecase.report

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.data.repository.MembersRepository
import com.captures2024.soongan.core.data.repository.ReportRepository
import com.captures2024.soongan.core.domain.runSuspendCatching
import com.captures2024.soongan.core.model.utils.ReportTargetType
import com.captures2024.soongan.core.model.utils.ReportType
import javax.inject.Inject

class PostReportUseCase
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val reportRepository: ReportRepository,
    private val membersRepository: MembersRepository,
) {

    suspend operator fun invoke(params: Params): Result<Boolean> = runSuspendCatching {
        val reportInfoDto = reportRepository.postReport(
            targetId = params.targetId,
            targetType = params.targetType,
            reportType = params.reportType,
            reason = params.reason,
        )

        membersRepository.updateReportHistories(reportInfoDto.reportHistories)

        analyticsHelper.d { "currentMember reportHistories : ${membersRepository.currentMember.value?.reportHistories}" }

        return@runSuspendCatching true
    }

    data class Params(
        val targetId: Long,
        val targetType: ReportTargetType,
        val reportType: ReportType,
        val reason: String? = null,
    )
}
